package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.DietGoal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevel;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UserDTO {

    @Pattern(regexp = "[A-ZĆŁŚŹŻ][a-ząćęłńóśźż]{1,14}",
            message = "Imię musi zaczynać się z dużej litery i składać się z 2 do 15 znaków alfabetu.")
    private String name;
    @NotNull(message = "Wybierz płeć.")
    private Sex sex;
    @NotNull(message = "Wybierz lub wpisz poprawną datę urodzenia. Wskazane minimum 18 lat.")
    @Past(message = "Wybierz datę z przeszłości.")
    private LocalDate dateOfBirth;
    @NotNull(message = "Wzrost musi mieć od 90 do 255 cm.")
    @Min(value = 90, message = "Minimalny wzrost to 90 cm.")
    @Max(value = 255, message = "Maksymalny wzrost to 255 cm.")
    private Integer heightInCms;
    @NotNull(message = "Waga musi mieć od 25 do 360 kg.")
    @Min(value = 25, message = "Minimalna waga to 25 kg.")
    @Max(value = 360, message = "Maksymalna waga to 360 kg.")
    private Integer weightInKgs;
    @NotNull(message = "Wybierz swój dzienny poziom aktywności fizycznej.")
    private PhysicalActivityLevel pal;
    @NotNull(message = "Zaznacz jaki masz cel diety.")
    private DietGoal dietGoal;
    private Integer bmr;
    private Integer caloriesToEatDaily;

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

    public UserDTO setHeightInCms(Integer heightInCms) {
        this.heightInCms = heightInCms;
        return this;
    }

    public UserDTO setWeightInKgs(Integer weightInKgs) {
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

    public UserDTO setBmr(Integer bmr) {
        this.bmr = bmr;
        return this;
    }

    public UserDTO setCaloriesToEatDaily(Integer caloriesToEatDaily) {
        this.caloriesToEatDaily = caloriesToEatDaily;
        return this;
    }
}
