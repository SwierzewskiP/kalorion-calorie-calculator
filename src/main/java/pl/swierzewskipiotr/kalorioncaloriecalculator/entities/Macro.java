package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Macro {
    @Min(value = 0, message = "Minimum 0 kcal.")
    @Max(value = 900, message = "Nie istnieje nic, co ma więcej niż 900 kcal w 100 g.")
    private int kcal;
    @Min(value = 0, message = "Minimum 0 g białka.")
    @Max(value = 100, message = "Nie istnieje nic, co ma więcej niż 100 g białka w 100 g.")
    private double protein;
    @Min(value = 0, message = "Minimum 0 g tłuszczu.")
    @Max(value = 100, message = "Nie istnieje nic, co ma więcej niż 100 g tłuszczu w 100 g.")
    private double fat;
    @Min(value = 0, message = "Minimum 0 g węglowodanów.")
    @Max(value = 100, message = "Nie istnieje nic, co ma więcej niż 100 g węglowodanów w 100 g.")
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
