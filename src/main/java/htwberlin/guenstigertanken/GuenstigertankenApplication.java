package htwberlin.guenstigertanken;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuenstigertankenApplication {

	@RequestMapping("/")
	public String hello() {
		return "Hello World!";
	}
}
