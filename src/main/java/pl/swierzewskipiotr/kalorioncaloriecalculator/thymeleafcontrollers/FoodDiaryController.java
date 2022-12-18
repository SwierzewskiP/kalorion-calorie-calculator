package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.MealService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.TotalMacroService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Controller
public class FoodDiaryController {

    private final UserService userService;
    private final MealService mealService;
    private final TotalMacroService totalMacroService;

    @GetMapping("/fooddiary/{date}")
    public String getFoodDiary(@PathVariable LocalDate date, OAuth2AuthenticationToken authentication, Model model) {
        Integer userId = authentication.getPrincipal().getAttribute("id");
        User user = userService.getUserEntity(userId);
        int userCalories = userService.getCalculatedCaloriesByUserId(userId);

        var allMeals = mealService.getAllMealsForGivenDay(user, date);
        var allMealsWithTotalMacro = totalMacroService.calculateTotalForEachMealTypeForGivenDay(allMeals);

        LocalDate oneDayBack = date.minusDays(1);
        LocalDate oneDayForth = date.plusDays(1);

        model.addAttribute("yesterday", LocalDate.now().minusDays(1));
        model.addAttribute("tomorrow", LocalDate.now().plusDays(1));
        model.addAttribute("oneDayBack", oneDayBack);
        model.addAttribute("oneDayBackLabel", oneDayBack
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        model.addAttribute("oneDayForth", oneDayForth);
        model.addAttribute("oneDayForthLabel", oneDayForth
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        model.addAttribute("givenDay", date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        model.addAttribute("allMealsWithTotalMacro", allMealsWithTotalMacro);
        model.addAttribute("totalMacroForGivenDay", totalMacroService.calculateTotalMacroForGivenDay(allMealsWithTotalMacro));
        model.addAttribute("macroRatio", totalMacroService.calculateMacroRatioPerUserCalories(userCalories));

        return "fooddiary";
    }
}