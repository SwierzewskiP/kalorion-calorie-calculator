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
    @ManyToOne
    private Product product;
    @Embedded
    private Macro macro;
    private int weight;
    @ManyToOne
    private User user;
    private MealType mealType;
    private LocalDate date;

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
