package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.ProductDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FrontendController {
    private final HelloService helloService;
    private final UserService userService;
    private final TotalMacroService totalMacroService;
    private final MealService mealService;
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("greeting", helloService.fetchHelloMessage());

        return "index";
    }

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

    @GetMapping("/yourcalories")
    public String getYourCalories(Model model, OAuth2AuthenticationToken authentication) {
        Integer userId = authentication.getPrincipal().getAttribute("id");
        model.addAttribute("name", userService.getNameByUserId(userId));
        model.addAttribute("calculatedCalories", userService.getCalculatedCaloriesByUserId(userId));
        model.addAttribute("calculatedBMR", userService.getBMRbyUserId(userId));

        return "yourcalories";
    }

    @GetMapping("/fooddiary")
    public String getFoodDiary(OAuth2AuthenticationToken authentication, Model model) {
        Integer userId = authentication.getPrincipal().getAttribute("id");
        User user = userService.getUserEntity(userId);
        int userCalories = userService.getCalculatedCaloriesByUserId(userId);
        var allMeals = mealService.getAllMealsForGivenDay(user, LocalDate.now());
        var allMealsWithTotalMacro = totalMacroService.calculateTotalForEachMealTypeForGivenDay(allMeals);
        model.addAttribute("allMealsWithTotalMacro", allMealsWithTotalMacro);
        model.addAttribute("totalMacroForGivenDay", totalMacroService.calculateTotalMacroForGivenDay(allMealsWithTotalMacro));
        model.addAttribute("macroRatio", totalMacroService.calculateMacroRatioPerUserCalories(userCalories));

        return "fooddiary";
    }

    @GetMapping("/fooddiary/delete/{id}")
    public String getMealToDelete(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/fooddiary";
    }

    @GetMapping("/products")
    public String products(Model model) {
        final List<ProductDTO> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);

        return "products";
    }
}
