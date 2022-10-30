package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.Getter;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class User {
    @Id
    private Integer id;
    private String name;
    private Sex sex;
    private LocalDate dateOfBirth;
    private int heightInCms;
    private int weightInKgs;
    private int bmr;
    private int caloriesToEatDaily;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Meal> meals = new ArrayList<>();

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public User setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public User setHeightInCms(int heightInCms) {
        this.heightInCms = heightInCms;
        return this;
    }

    public User setWeightInKgs(int weightInKgs) {
        this.weightInKgs = weightInKgs;
        return this;
    }

    public User setBmr(int bmr) {
        this.bmr = bmr;
        return this;
    }

    public User setCaloriesToEatDaily(int caloriesToEatDaily) {
        this.caloriesToEatDaily = caloriesToEatDaily;
        return this;
    }
}
