package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private int proteinsPer100g;
    private int fatsPer100g;
    private int carbsPer100g;

    public int getCalories() {
        return proteinsPer100g*4 + fatsPer100g*9 + carbsPer100g*4;
    }
}
