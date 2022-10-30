package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Macro {
    private int kcal;
    private double protein;
    private double fat;
    private double carb;

    public Macro setKcal(int kcal) {
        this.kcal = kcal;
        return this;
    }

    public Macro setProtein(double protein) {
        this.protein = protein;
        return this;
    }

    public Macro setFat(double fat) {
        this.fat = fat;
        return this;
    }

    public Macro setCarb(double carb) {
        this.carb = carb;
        return this;
    }
}
