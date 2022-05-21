package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
@Service
public class UserCalculationsService {

    public int calculateAge(UserDTO userDTO) {
        return Period.between(userDTO.getDateOfBirth(), LocalDate.now()).getYears();
    }

    //BMR - Basal Metabolic Rate by Mifflin
    public int calculateAndSetBasalMetabolicRate(UserDTO userDTO) {
        int age = calculateAge(userDTO);
        double bmrRounded;
        double mifflinBMRFormula
                = (10 * userDTO.getWeightInKgs()) + (6.25 * userDTO.getHeightInCms()) - (5 * age);
        switch (userDTO.getSex()) {
            case MALE:
                bmrRounded = BigDecimal.valueOf(mifflinBMRFormula + 5)
                        .setScale(0, RoundingMode.HALF_UP)
                        .doubleValue();
                userDTO.setBmr((int) bmrRounded);
                return userDTO.getBmr();
            case FEMALE:
                bmrRounded = BigDecimal.valueOf(mifflinBMRFormula - 161)
                        .setScale(0, RoundingMode.HALF_UP)
                        .doubleValue();
                userDTO.setBmr((int) bmrRounded);
                return userDTO.getBmr();
            default:
                throw new IllegalStateException("Unexpected value: " + userDTO.getSex());
        }
    }

    //TDEE (Total Daily Energy Expenditure) = BMR * PAL (Physical Activity Level)
    public double calculateTotalDailyEnergyExpenditure(UserDTO userDTO) {
        return calculateAndSetBasalMetabolicRate(userDTO) * userDTO.getPal().getValue();
    }

    public int calculateAndSetCaloriesToEatDaily(UserDTO userDTO) {
        double preciseValue = calculateTotalDailyEnergyExpenditure(userDTO) * userDTO.getDietGoal().getValue();
        double roundedValue = BigDecimal.valueOf(preciseValue)
                .setScale(0, RoundingMode.HALF_UP)
                .doubleValue();
        userDTO.setCaloriesToEatDaily((int) roundedValue);
        return userDTO.getCaloriesToEatDaily();
    }
}