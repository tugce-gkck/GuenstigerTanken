package htwberlin.guenstigerTankenneu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllerGuenstigerTanken {

    @RequestMapping("/")
    public String hello() {
        return "Hello World!";
    }
}
