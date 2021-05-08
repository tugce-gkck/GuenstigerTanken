package htwberlin.guenstigertanken;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GuenstigertankenController {
	private final List<Tanken> tanken;

	public GuenstigertankenController(List<Tanken> tanken){
		this.tanken = tanken;
	}
	public GuenstigertankenController(){
		this.tanken = new ArrayList<Tanken>();
		this.tanken.add(new Tanken(1,"2021-05-17 15:07:00","aral", "Heilbronn",10.5,1.7));
	}

	@RequestMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@GetMapping("/tanken")
	public List<Tanken> getTanken() {
		return tanken;
	}

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


