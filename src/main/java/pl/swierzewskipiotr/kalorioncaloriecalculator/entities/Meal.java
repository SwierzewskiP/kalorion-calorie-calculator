package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Product product;
    @Transient
    private Macro macro;
    private int weight;
    @ManyToOne
    private User user;
    private MealType mealType;
    private LocalDate date;

    public Macro getMacro() {
        this.macro = calculateMacroPerGivenWeight();

        return macro;
    }

    private Macro calculateMacroPerGivenWeight() {
        int kcal = Math.round(this.product.macroPer100g.getKcal() * this.weight / 100);
        double protein = this.product.macroPer100g.getProtein() * this.weight / 100;
        double fat = this.product.macroPer100g.getFat() * this.weight / 100;
        double carb = this.product.macroPer100g.getCarb() * this.weight / 100;
        Macro macro = new Macro(kcal, protein, fat, carb);

        return macro;
    }

    public Meal setId(Long id) {
        this.id = id;
        return this;
    }

    public Meal setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Meal setMacro(Macro macro) {
        this.macro = macro;
        return this;
    }

    public Meal setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public Meal setUser(User user) {
        this.user = user;
        return this;
    }

    public Meal setMealType(MealType mealType) {
        this.mealType = mealType;
        return this;
    }

    public Meal setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return product.getName() + " w: " + mealType.getDescription();
    }
}
