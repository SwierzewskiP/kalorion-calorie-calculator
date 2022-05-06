package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private Integer githubId;
    private String name;
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
