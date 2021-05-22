package htwberlin.guenstigertanken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GuenstigertankenController {
	private final TankenRepository repository;

	public GuenstigertankenController(TankenRepository repository){
		this.repository = repository;
	}
	@GetMapping("/")
	public String showAll(Model model) {
		model.addAttribute("name","Kunde");
		model.addAttribute("tanken", repository.findAll());
		return "index";
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/tanken")
	public List<Tanken> all() {
		return (List<Tanken>) this.repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/tanken")
	Tanken newTanken(@RequestBody Tanken newTanken) {
		return repository.save(newTanken);
	}

	@GetMapping("/tanken/{id}")
	Tanken one(@PathVariable Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new TankenNotFoundException(id));
	}

	@PutMapping("/employees/{id}")
	Tanken replaceTanken(@RequestBody Tanken newTanken, @PathVariable Long id) {

		return repository.findById(id)
				.map(tanken -> {
					tanken.setName(newTanken.getName());
					tanken.setId(newTanken.getId());
					tanken.setDate(newTanken.getDate());
					tanken.setCity(newTanken.getCity());
					tanken.setDistance(newTanken.getDistance());
					tanken.setPrice(newTanken.getPrice());
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


