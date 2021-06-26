package htwberlin.guenstigertanken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GuenstigertankenRestController {
    private final TankenRepository repository;
    private final UserRepository userRepository;
    private HashMap<String, String> sessions;

    public GuenstigertankenRestController(TankenRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
        this.sessions = new HashMap<String, String>();
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/tanken")
    public List<Tanken> all(@RequestParam("session") String session) {
        String username = validateSession(session);
        return (List<Tanken>) this.repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/login")
    String login(@RequestBody User user) {

        User realUser = userRepository.findById(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername()));


        if(!realUser.getPassword().equals(user.getPassword())){
            throw new UserNotFoundException(user.getUsername());
        }

        for (HashMap.Entry<String, String> entry : sessions.entrySet()) {
           if (entry.getValue().equals(user.getUsername())) {
             return entry.getKey();
            }
        }

        String session = UUID.randomUUID().toString();
        sessions.put(session,user.getUsername());
        return session;

    }

    @PostMapping("/register")
    User register(@RequestBody User user) {

        if(!userRepository.findById(user.getUsername()).isEmpty()){
            throw new UserAlreadyRegisteredException(user.getUsername());
        }
        this.userRepository.save(user);

        return user;
    }

    @GetMapping("/user")
    String user(@RequestParam("session") String session) {
        if(!this.sessions.containsKey(session))
            throw new UserNotFoundException("");

        return this.sessions.get(session);
    }

    String validateSession(String session) {
        String username = this.sessions.get(session);
        try{
            User user = this.userRepository.findById(username)
                    .orElseThrow(() -> new UserNotFoundException(username));
        } catch(Exception e){
            throw new UserNotFoundException(username);
        }

        return username;
    }

    @PostMapping("/tanken")
    Tanken newTanken(@RequestBody Tanken newTanken, @RequestParam("session") String session) {
        String username = validateSession(session);
        return repository.save(newTanken);
    }

    @GetMapping("/tanken/{id}")
    Tanken one(@PathVariable Long id, @RequestParam("session") String session) {
        String username = validateSession(session);
        return repository.findById(id)
                .orElseThrow(() -> new TankenNotFoundException(id));
    }

    @PutMapping("/tanken/{id}")
    Tanken replaceTanken(@RequestBody Tanken newTanken, @PathVariable Long id, @RequestParam("session") String session) {
        String username = validateSession(session);
        return repository.findById(id)
                .map(tanken -> {
                    tanken.setName(newTanken.getName());
                    tanken.setId(newTanken.getId());
                    tanken.setDate(newTanken.getDate());
                    tanken.setCity(newTanken.getCity());
                    tanken.setDistance(newTanken.getDistance());
                    tanken.setPrice(newTanken.getPrice());
                    tanken.setWc(newTanken.isWc());
                    tanken.setRestaurant(newTanken.isRestaurant());
                    tanken.setCarwash(newTanken.isCarwash());
                    return repository.save(tanken);
                })
                .orElseGet(() -> {
                    newTanken.setId(id);
                    return repository.save(newTanken);
                });
    }

    @DeleteMapping("/tanken/{id}")
    void deleteEmployee(@PathVariable Long id, @RequestParam("session") String session) {
        String username = validateSession(session);
        repository.deleteById(id);
    }
}


