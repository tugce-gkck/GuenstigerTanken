package htwberlin.guenstigertanken;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuenstigertankenController {
	private List<Tanken> tanken;

	public GuenstigertankenController(){
		this.tanken = (new DatabaseSeeder()).readAll();
	}
	@GetMapping("/")
	public String hello() {
		return "Herzlich Willkommen bei Günstiger Tanken";
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/tanken")
	public List<Tanken> all() {
		return tanken;
	}
	// end::get-aggregate-root[]

	@PostMapping("/tanken")
	Tanken newTanken(@RequestBody Tanken newTanken) {
		tanken.add(newTanken);
		return newTanken;
	}

	@GetMapping("/tanken/{id}")
	Tanken one(@PathVariable Long id) {

		return tanken.get(0);
	}

	@DeleteMapping("/tanken/{id}")
	void deleteEmployee(@PathVariable Long id) {

	}
}


