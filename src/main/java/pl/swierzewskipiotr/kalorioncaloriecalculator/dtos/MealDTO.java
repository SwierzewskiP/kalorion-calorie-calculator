package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class MealDTO {
    private Long id;
    private Product product;
    private Macro macro;
    @Min(value = 1, message = "Minimalna waga to 1 gram.")
    @Max(value = 10000, message = "Maksymalna waga to 10000 g (10 kg).")
    private int weight;
    private Macro totalMacroTempVar;
}
