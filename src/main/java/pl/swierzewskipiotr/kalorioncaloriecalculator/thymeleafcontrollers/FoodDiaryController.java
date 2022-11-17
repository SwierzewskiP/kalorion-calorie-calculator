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

@RequiredArgsConstructor
@Controller
public class FoodDiaryController {

    private final UserService userService;
    private final MealService mealService;
    private final TotalMacroService totalMacroService;

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

    @GetMapping("/fooddiary/edit/{id}")
    public String getMealToEdit(@PathVariable("id") Long id, Model model) {
        MealDTO mealDTO = mealService.getMealDTObyId(id);

        model.addAttribute("mealDTO", mealDTO);

        return "editmeal";
    }

    @PostMapping("/fooddiary/update/{id}")
    public String postMealToUpdate(@PathVariable("id") Long id, @Valid MealDTO mealDTO, BindingResult result) {
        MealDTO mealTemp = mealService.getMealDTObyId(id);
        if (result.hasErrors()) {
            mealDTO.setProduct(mealTemp.getProduct());
            return "editmeal";
        }
        mealService.updateMeal(mealDTO);
        return "redirect:/fooddiary";
    }

    @GetMapping("/fooddiary/delete/{id}")
    public String getMealToDelete(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/fooddiary";
    }
}
