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

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UserDTO setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public UserDTO setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDTO setHeightInCms(int heightInCms) {
        this.heightInCms = heightInCms;
        return this;
    }

    public UserDTO setWeightInKgs(int weightInKgs) {
        this.weightInKgs = weightInKgs;
        return this;
    }

    public UserDTO setPal(PhysicalActivityLevel pal) {
        this.pal = pal;
        return this;
    }

    public UserDTO setDietGoal(DietGoal dietGoal) {
        this.dietGoal = dietGoal;
        return this;
    }

    public UserDTO setBmr(int bmr) {
        this.bmr = bmr;
        return this;
    }

    public UserDTO setCaloriesToEatDaily(int caloriesToEatDaily) {
        this.caloriesToEatDaily = caloriesToEatDaily;
        return this;
    }
}
