package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.HelloService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

@RequiredArgsConstructor
@Controller
@Log4j2
public class FrontendController {
    private final HelloService helloService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("greeting", helloService.fetchHelloMessage());
        return "index";
    }

    @GetMapping("/calculateintake")
    public String calculateIntake(Model model) {
        final UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "calculateintake";
    }

    @PostMapping("/calculateintake")
    public String postCalculateIntake(Model model, UserDTO userDTO) {
        userService.addNewUser(userDTO);
        return "redirect:/yourcalories";
    }

    @GetMapping("/yourcalories")
    public String yourCalories(Model model) {
        model.addAttribute("greeting", helloService.fetchHelloMessage());
        return "index2";
    }
}
