package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class UserCalculationsService {

    public int calculateAge(UserDTO userDTO) {
        return Period.between(userDTO.getDateOfBirth(), LocalDate.now()).getYears();
    }

    //BMR - Basal Metabolic Rate by Mifflin
    public int calculateBasalMetabolicRate(UserDTO userDTO) {
        double mifflinBMRFormula
                = (10 * userDTO.getWeightInKgs()) + (6.25 * userDTO.getHeightInCms()) - (5 * calculateAge(userDTO));
        switch (userDTO.getSex()) {
            case MALE:
                userDTO.setBmr((int) mifflinBMRFormula + 5);
                return userDTO.getBmr();
            case FEMALE:
                userDTO.setBmr((int) mifflinBMRFormula - 161);
                return userDTO.getBmr();
            default:
                throw new IllegalStateException("Unexpected value: " + userDTO.getSex());
        }
    }

    //TDEE (Total Daily Energy Expenditure) = BMR * PAL (Physical Activity Level)
    public double calculateTotalDailyEnergyExpenditure(UserDTO userDTO) {
        return calculateBasalMetabolicRate(userDTO) * userDTO.getPal().getValue();
    }

    public void calculateCaloriesToEatDaily(UserDTO userDTO) {
        double value = calculateTotalDailyEnergyExpenditure(userDTO) * userDTO.getDietGoal().getValue();
        userDTO.setCaloriesToEatDaily((int) value);
    }

    public void calculateBmrAndCaloriesToEatDaily(UserDTO userDTO) {
        calculateCaloriesToEatDaily(userDTO);
    }
}