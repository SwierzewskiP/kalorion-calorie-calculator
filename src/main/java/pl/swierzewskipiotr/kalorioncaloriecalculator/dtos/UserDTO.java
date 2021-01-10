package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevels;
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
    private PhysicalActivityLevels pal;

    private int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    //BMR - Basal Metabolic Rate by Mifflin
    public double getBMR() {
        double mifflinBMRFormula = (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge());
        switch (sex) {
            case MALE:
                return mifflinBMRFormula + 5;
            case FEMALE:
                return mifflinBMRFormula - 161;
            default:
                throw new IllegalStateException("Unexpected value: " + sex);
        }
//        if (sex.equals(Sex.MALE)) {
//            return mifflinBMRFormula + 5;
//        } else {
//            return mifflinBMRFormula - 161;
//        }
    }

    //TDEE (Total Daily Energy Expenditure) = BMR * PAL (Physical Activity Level)
    public double getTDEE() {
        return getBMR() * pal.getValue();
    }
}
