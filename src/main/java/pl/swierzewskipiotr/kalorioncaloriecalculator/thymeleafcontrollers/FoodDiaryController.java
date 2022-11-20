package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.MealDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.MealService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.TotalMacroService;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.UserService;

import javax.validation.Valid;
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

    @GetMapping("/fooddiary/{date}/edit/{id}")
    public String getMealToEdit(@PathVariable LocalDate date, @PathVariable("id") Long id, Model model) {
        MealDTO mealDTO = mealService.getMealDTObyId(id);

        model.addAttribute("mealDTO", mealDTO);

        return "editmeal";
    }

    @PostMapping("/fooddiary/{date}/update/{id}")
    public String postMealToUpdate(@PathVariable LocalDate date, @PathVariable("id") Long id,
                                   @Valid MealDTO mealDTO, BindingResult result) {
        MealDTO mealTemp = mealService.getMealDTObyId(id);
        if (result.hasErrors()) {
            mealDTO.setProduct(mealTemp.getProduct());
            return "editmeal";
        }
        mealService.updateMeal(mealDTO);
        return "redirect:/fooddiary/" + date;
    }

    @GetMapping("/fooddiary/{date}/delete/{id}")
    public String getMealToDelete(@PathVariable LocalDate date, @PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/fooddiary/" + date;
    }
}
