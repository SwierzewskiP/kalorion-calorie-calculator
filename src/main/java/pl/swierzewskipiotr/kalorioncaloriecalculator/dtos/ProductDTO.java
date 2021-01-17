package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private int kcalPer100g;
    private double proteinsPer100g;
    private double fatsPer100g;
    private double carbsPer100g;
}
