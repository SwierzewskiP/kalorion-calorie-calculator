package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.DietGoal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevel;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;
    private Sex sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private int heightInCms;
    private int weightInKgs;
    private PhysicalActivityLevel pal;
    private DietGoal dietGoal;
    private int bmr;
    private int caloriesToEatDaily;
}
