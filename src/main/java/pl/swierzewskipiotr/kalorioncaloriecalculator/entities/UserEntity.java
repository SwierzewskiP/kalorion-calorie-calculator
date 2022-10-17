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
@Entity(name = "user")
public class UserEntity {
    @Id
    private Integer githubId;
    private String name;
    private Sex sex;
    private LocalDate dateOfBirth;
    private int heightInCms;
    private int weightInKgs;
    private int bmr;
    private int caloriesToEatDaily;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<MealEntity> meals = new ArrayList<>();

    public UserEntity setGithubId(Integer githubId) {
        this.githubId = githubId;
        return this;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public UserEntity setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserEntity setHeightInCms(int heightInCms) {
        this.heightInCms = heightInCms;
        return this;
    }

    public UserEntity setWeightInKgs(int weightInKgs) {
        this.weightInKgs = weightInKgs;
        return this;
    }

    public UserEntity setBmr(int bmr) {
        this.bmr = bmr;
        return this;
    }

    public UserEntity setCaloriesToEatDaily(int caloriesToEatDaily) {
        this.caloriesToEatDaily = caloriesToEatDaily;
        return this;
    }
}
