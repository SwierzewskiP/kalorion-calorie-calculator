package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class UserFormController {

    private final UserService userService;

    @GetMapping("/userform")
    public String getUserForm(Model model, OAuth2AuthenticationToken authentication) {
        Integer userId = authentication.getPrincipal().getAttribute("id");
        if (userService.existsById(userId)) {
            return "redirect:/fooddiary";
        }
        final UserDTO userDTO = new UserDTO();

        model.addAttribute("userDTO", userDTO);

        return "userform";
    }

    @PostMapping("/userform")
    public String postUserForm(@Valid UserDTO userDTO, BindingResult result, OAuth2AuthenticationToken authentication) {
        if (result.hasErrors()) {
            return "userform";
        }
        userService.addNewUser(userDTO, authentication);

        return "redirect:/yourcalories";
    }
}
