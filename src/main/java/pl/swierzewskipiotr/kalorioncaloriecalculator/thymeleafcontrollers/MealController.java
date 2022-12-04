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
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.MealService;

import javax.validation.Valid;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class MealController {

    private final MealService mealService;

    @GetMapping("/fooddiary/{date}/{mealType}/add")
    public String getMealToCreateNew(@PathVariable LocalDate date, @PathVariable MealType mealType, Model model) {
        final MealDTO mealDTO = new MealDTO();
        mealDTO.setMealType(mealType);

        model.addAttribute("mealDTO", mealDTO);

        return "createnewmeal";
    }

    @PostMapping("/fooddiary/{date}/{mealType}/add")
    public String postMealToCreateNew(@PathVariable LocalDate date, @PathVariable MealType mealType, @Valid MealDTO mealDTO, BindingResult result, OAuth2AuthenticationToken authentication) {
        if (result.hasErrors()) {
            return "createnewmeal";
        }
        mealService.addMealWithNewProduct(mealDTO, authentication, date, mealType);

        return "redirect:/fooddiary/" + date;
    }
}
