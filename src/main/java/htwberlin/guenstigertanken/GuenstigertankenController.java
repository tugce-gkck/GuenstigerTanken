package htwberlin.guenstigertanken;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class GuenstigertankenController {
	private final TankenRepository repository;
	private final UserRepository userRepository;

	public GuenstigertankenController(TankenRepository repository, UserRepository userRepository){
		this.repository = repository;
		this.userRepository = userRepository;
	}
	@GetMapping("/")
	public String loginPage(Model model) {

		return "index";
	}

	@GetMapping("/main")
	public String showAll(Model model) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");

		model.addAttribute("now",dtf.format(LocalDateTime.now()));
		model.addAttribute("tanken", repository.findAll());
		return "main";
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/tanken")
	public List<Tanken> all() {
		return (List<Tanken>) this.repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/user")
	User validation(@RequestBody User user) {
		Example<User> example = Example.of(user);

		Optional<User> valid = userRepository.findOne(example);

		if(valid.isEmpty() || !( valid.get().getUsername() == user.getUsername() && valid.get().getPassword() == user.getPassword())){
			throw new UserNotFoundException(user.getUsername());
		}
		return new User(user.getUsername(),"");
	}

	@PostMapping("/tanken")
	Tanken newTanken(@RequestBody Tanken newTanken) {
		return repository.save(newTanken);
	}

	@GetMapping("/tanken/{id}")
	Tanken one(@PathVariable Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new TankenNotFoundException(id));
	}

	@PutMapping("/tanken/{id}")
	Tanken replaceTanken(@RequestBody Tanken newTanken, @PathVariable Long id) {

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
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}


