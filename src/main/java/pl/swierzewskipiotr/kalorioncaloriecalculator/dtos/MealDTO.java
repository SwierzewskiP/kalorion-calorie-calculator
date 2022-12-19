package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
public class MealDTO {
    private Long id;
    @Valid
    private Product product;
    private Macro macro;
    @Min(value = 1, message = "Minimalna waga to 1 gram.")
    @Max(value = 10000, message = "Maksymalna waga to 10000 g (10 kg).")
    private int weight;
    private User user;
    private MealType mealType;
    private LocalDate date;
    private Macro totalMacroTempVar;
}
