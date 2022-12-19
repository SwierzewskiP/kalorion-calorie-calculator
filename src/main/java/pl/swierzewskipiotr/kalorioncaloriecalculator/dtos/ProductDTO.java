package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;

import javax.validation.Valid;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    @Valid
    private Macro macroPer100g;
}
