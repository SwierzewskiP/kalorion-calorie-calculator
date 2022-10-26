package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.Getter;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity(name = "meal")
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int kcal;
    private double protein;
    private double fat;
    private double carb;
    private int weight;
    @ManyToOne
    private UserEntity user;
    private MealType mealType;
    private LocalDate date;

    public MealEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public MealEntity setName(String name) {
        this.name = name;
        return this;
    }

    public MealEntity setKcal(int kcal) {
        this.kcal = kcal;
        return this;
    }

    public MealEntity setProtein(double protein) {
        this.protein = protein;
        return this;
    }

    public MealEntity setFat(double fat) {
        this.fat = fat;
        return this;
    }

    public MealEntity setCarb(double carb) {
        this.carb = carb;
        return this;
    }

    public MealEntity setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public MealEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public MealEntity setMealType(MealType mealType) {
        this.mealType = mealType;
        return this;
    }

    public MealEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
