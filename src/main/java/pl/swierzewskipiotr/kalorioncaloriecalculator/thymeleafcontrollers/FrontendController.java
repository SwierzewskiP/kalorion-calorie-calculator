package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.HelloService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

import java.time.LocalDate;

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
        userDTO.setDateOfBirth(LocalDate.of(1999, 1, 1));
        userDTO.setHeightInCms(170);
        userDTO.setWeightInKgs(65);
        model.addAttribute("userDTO", userDTO);
        return "calculateintake";
    }

    @PostMapping("/calculateintake")
    public String postCalculateIntake(Model model, UserDTO userDTO) {
        Long userId = userService.addNewUser(userDTO);
        return "redirect:/yourcalories/" + userId;
    }

    @GetMapping("/yourcalories/{userId}")
    public String yourCalories(Model model, @PathVariable Long userId) {
        model.addAttribute("calculatedCalories", userService
                .getCalculatedCaloriesByUserId(userId));
        return "yourcalories";
    }
}
