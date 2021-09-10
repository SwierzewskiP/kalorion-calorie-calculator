package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.DietGoal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevel;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String userName;
    private Sex sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private int heightInCms;
    private int weightInKgs;
    private int bmr;
    private int calculatedCalorieIntake;
    private PhysicalActivityLevel pal;
    private DietGoal dietGoal;

    private int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    //BMR - Basal Metabolic Rate by Mifflin
    public int getBMR() {
        double mifflinBMRFormula = (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge());
        switch (sex) {
            case MALE:
                return (int)mifflinBMRFormula + 5;
            case FEMALE:
                return (int)mifflinBMRFormula - 161;
            default:
                throw new IllegalStateException("Unexpected value: " + sex);
        }
    }

    //TDEE (Total Daily Energy Expenditure) = BMR * PAL (Physical Activity Level)
    public double getTDEE() {
        return getBMR() * pal.getValue();
    }

    //it's named later calculatedCalorieIntake for clarity
    public int getTDEEmodifiedByDietGoal() {
        double value = getTDEE() * dietGoal.getValue();
        return (int)value;
    }
}
