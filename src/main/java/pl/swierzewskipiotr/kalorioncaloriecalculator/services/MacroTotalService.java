package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.MealDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MacroTotalService {
    public List<MealDTO> getTotalMacroForMealType(List<MealDTO> meals) {
        int sumKcal = 0;
        double sumProtein = 0;
        double sumFat = 0;
        double sumCarb = 0;
        for (MealDTO meal : meals) {
            sumKcal += meal.getMacro().getKcal();
            sumProtein += meal.getMacro().getProtein();
            sumFat += meal.getMacro().getFat();
            sumCarb += meal.getMacro().getCarb();
        }
        if (!meals.isEmpty()) {
            meals.get(0).setTotalMacroTempVar(new Macro(sumKcal, sumProtein, sumFat, sumCarb));
        }
        return meals;
    }

    public Map<SimpleImmutableEntry<MealType, Macro>, List<MealDTO>> getTotalForEachMealTypeForGivenDay(Map<MealType, List<MealDTO>> allMeals) {
        Map<SimpleImmutableEntry<MealType, Macro>, List<MealDTO>> allMealsWithTotals = new LinkedHashMap<>();
        for (MealType type : MealType.values()) {
            if (allMeals.get(type).size() != 0) {
                Macro macro = getTotalMacroForMealType(allMeals.get(type)).get(0).getTotalMacroTempVar();
                var tuple = new SimpleImmutableEntry<>(type, macro);
                allMealsWithTotals.put(tuple, allMeals.get(type));
            } else {
                var tuple = new SimpleImmutableEntry<>(type, new Macro(0, 0.0, 0.0, 0.0));
                allMealsWithTotals.put(tuple, allMeals.get(type));
            }
        }
        return allMealsWithTotals;
    }
}
