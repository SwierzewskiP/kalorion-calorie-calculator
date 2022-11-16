package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    @Valid
    @NotNull(message = "Podaj wartość z tabeli produktu.")
    private Macro macroPer100g;
}
