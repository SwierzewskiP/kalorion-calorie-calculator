package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private Sex sex;
    private LocalDate dateOfBirth;
    private double weight;
    private double height;
}
