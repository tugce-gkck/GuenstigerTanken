package htwberlin.guenstigertanken;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuenstigertankenController {

	@RequestMapping("/")
	public String hello() {
		return "Hello World!";
	}

	@RequestMapping("/getTanken")
	public String getTanken() {
		return "GetTanken!";
	}

	@RequestMapping("/addTanken")
	public String addTanken() {
		return "addTanken!";
	}

	@RequestMapping("/deleteTanken")
	public String deleteTanken() {
		return "DeleteTanken!";
	}

	@RequestMapping("/updateTanken")
	public String updateTanken() {
		return "Update Tanken!";
	}
}


