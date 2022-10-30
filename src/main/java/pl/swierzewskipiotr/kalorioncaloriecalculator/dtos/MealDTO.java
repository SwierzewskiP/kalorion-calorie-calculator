package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;

@Data
public class MealDTO {
    private Long id;
    private Product product;
    private Macro macro;
    private int weight;
}
