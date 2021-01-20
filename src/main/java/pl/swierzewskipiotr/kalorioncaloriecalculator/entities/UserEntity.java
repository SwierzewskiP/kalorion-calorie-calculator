package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private Sex sex;
    private LocalDate dateOfBirth;
    private int heightInCms;
    private int weightInKgs;
    private int bmr;
    private int calculatedCaloricIntake;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private Set<MealEntity> meals = new HashSet<>();
}
