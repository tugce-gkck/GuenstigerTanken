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
	private final String jsPath = "../../resources/templates/js";

	public GuenstigertankenController(TankenRepository repository, UserRepository userRepository){
		this.repository = repository;
		this.userRepository = userRepository;
	}
	@GetMapping("/")
	public String loginPage(Model model, @RequestParam("session") String session) {
		model.addAttribute("jsPath", jsPath);
		if(session.equals("")){
			return "index";
		} else{
			model.addAttribute("session",session);
			return "main";
		}
	}

}


