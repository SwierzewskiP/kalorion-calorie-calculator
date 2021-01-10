package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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
    private double heightInCms;
    private double weightInKgs;
    private double calculatedCaloricIntake;
}
