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
	public String loginPage(Model model, @RequestParam(defaultValue = "") String session) {
		if(session == null || session.equals("")){
			return "index";
		} else{
			model.addAttribute("session",session);
			return "main";
		}
	}

	@GetMapping("/{id}")
	public String singleTanken(Model model, @PathVariable Long id,@RequestParam("session") String session) {
		if(session == null || session.equals("")){
			return "error";
		} else{
			model.addAttribute("session",session);
			return "single";
		}
	}

	@GetMapping("/create")
	public String createPage(Model model, @RequestParam(defaultValue = "") String session) {
		if(session == null || session.equals("")){
			return "error";
		} else{
			model.addAttribute("session",session);
			return "create";
		}
	}
	@GetMapping("/error")
	public String loginPage() {
		return "error";
	}

}


