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
public class TotalMacroService {

    private static final double DEFAULT_DIET_PROTEIN_PERCENT = 0.2;
    private static final double DEFAULT_DIET_FAT_PERCENT = 0.3;
    private static final double DEFAULT_DIET_CARB_PERCENT = 0.5;
    private static final int KCAL_PER_GRAM_OF_PROTEIN = 4;
    private static final int KCAL_PER_GRAM_OF_FAT = 9;
    private static final int KCAL_PER_GRAM_OF_CARB = 4;
    public List<MealDTO> calculateTotalMacroForMealType(List<MealDTO> meals) {
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

    public Map<SimpleImmutableEntry<MealType, Macro>, List<MealDTO>> calculateTotalForEachMealTypeForGivenDay(Map<MealType, List<MealDTO>> allMeals) {
        Map<SimpleImmutableEntry<MealType, Macro>, List<MealDTO>> allMealsWithTotals = new LinkedHashMap<>();
        for (MealType type : MealType.values()) {
            if (allMeals.get(type).size() != 0) {
                Macro macro = calculateTotalMacroForMealType(allMeals.get(type)).get(0).getTotalMacroTempVar();
                var tuple = new SimpleImmutableEntry<>(type, macro);
                allMealsWithTotals.put(tuple, allMeals.get(type));
            } else {
                var tuple = new SimpleImmutableEntry<>(type, new Macro(0, 0.0, 0.0, 0.0));
                allMealsWithTotals.put(tuple, allMeals.get(type));
            }
        }
        return allMealsWithTotals;
    }

    public Macro calculateTotalMacroForGivenDay(Map<SimpleImmutableEntry<MealType, Macro>, List<MealDTO>> allMealsWithTotals) {
        int sumKcal = 0;
        double sumProtein = 0;
        double sumFat = 0;
        double sumCarb = 0;
        for (SimpleImmutableEntry<MealType, Macro> tuple : allMealsWithTotals.keySet()) {
            sumKcal += tuple.getValue().getKcal();
            sumProtein += tuple.getValue().getProtein();
            sumFat += tuple.getValue().getFat();
            sumCarb += tuple.getValue().getCarb();
        }
        return new Macro(sumKcal, sumProtein, sumFat, sumCarb);
    }

    public Macro calculateMacroRatioPerUserCalories(int caloriesToEatDaily) {
        double protein = caloriesToEatDaily * DEFAULT_DIET_PROTEIN_PERCENT / KCAL_PER_GRAM_OF_PROTEIN;
        double fat = caloriesToEatDaily * DEFAULT_DIET_FAT_PERCENT / KCAL_PER_GRAM_OF_FAT;
        double carb = caloriesToEatDaily * DEFAULT_DIET_CARB_PERCENT / KCAL_PER_GRAM_OF_CARB;

        return new Macro(caloriesToEatDaily, Math.round(protein), Math.round(fat), Math.round(carb));
    }
}
