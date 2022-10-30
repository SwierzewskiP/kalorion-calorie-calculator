package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;

@Data
public class ProductDTO {
    private String name;
    private Macro macroPer100g;
}
