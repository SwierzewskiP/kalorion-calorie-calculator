package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import lombok.Data;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevels;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

@Data
public class UserDTO {
    private String userName;
    private Sex sex;
    private LocalDate dateOfBirth;
    private int weightInKgs;
    private int heightInCms;
    private PhysicalActivityLevels pal;

    //TDEE - Total Daily Energy Expenditure
    public double getTDEE() {
        return pal.getDisplayNumber() * getBMR();
    }

    //BMR - BasalMetabolicRate
    public double getBMR() {
//        double bmr;
//        switch (sex) {
//            case MALE:
//                bmr = (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge()) + 5;
//                break;
//            case FEMALE:
//                bmr = (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge()) - 161;
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + sex);
//        }
//        return bmr;
        if (sex.equals(Sex.MALE)) {
            return (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge()) + 5;
        } else {
            return (10 * weightInKgs) + (6.25 * heightInCms) - (5 * getAge()) - 161;
        }
    }

    private int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }
}
