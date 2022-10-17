package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;

@Data
public class MealDTO {

    private String name;
    private int kcal;
    private double protein;
    private double fat;
    private double carb;
    private int weight;
}
