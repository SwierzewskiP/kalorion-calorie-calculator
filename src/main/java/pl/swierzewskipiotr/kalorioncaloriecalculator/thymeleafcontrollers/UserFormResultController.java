package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class UserFormResultController {

    private final UserService userService;

    @GetMapping("/yourcalories")
    public String getYourCalories(Model model, OAuth2AuthenticationToken authentication) {
        Integer userId = authentication.getPrincipal().getAttribute("id");
        LocalDate today = LocalDate.now();

        model.addAttribute("name", userService.getNameByUserId(userId));
        model.addAttribute("calculatedCalories", userService.getCalculatedCaloriesByUserId(userId));
        model.addAttribute("calculatedBMR", userService.getBMRbyUserId(userId));
        model.addAttribute("today", today);

        return "yourcalories";
    }
}
