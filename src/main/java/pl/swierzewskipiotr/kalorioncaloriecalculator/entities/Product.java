package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max = 100, message
            = "Nazwa musi się składać z minimum 2, a maksimum 100 znaków.")
    @NotBlank(message = "Podaj oficjalną nazwę produktu - będzie ona obowiązywać dla wszystkich użytkowników i NIE można jej później zmienić.")
    private String name;
    @Valid
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