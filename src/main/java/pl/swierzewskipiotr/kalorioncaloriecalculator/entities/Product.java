package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @AttributeOverrides({
            @AttributeOverride(name = "kcal", column = @Column(name = "kcal_per_100g")),
            @AttributeOverride(name = "protein", column = @Column(name = "protein_per_100g")),
            @AttributeOverride(name = "fat", column = @Column(name = "fat_per_100g")),
            @AttributeOverride(name = "carb", column = @Column(name = "carb_per_100g"))
    })
    Macro macroPer100g;

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setMacroPer100g(Macro macroPer100g) {
        this.macroPer100g = macroPer100g;
        return this;
    }
}