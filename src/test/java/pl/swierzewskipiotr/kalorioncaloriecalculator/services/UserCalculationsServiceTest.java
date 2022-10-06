package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.DietGoal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.PhysicalActivityLevel;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserCalculationsServiceTest {

    UserCalculationsService calculationsService;
    private UserDTO maleUser;
    private UserDTO femaleUser;

    @BeforeEach
    void setUp() {
        //given
        calculationsService = new UserCalculationsService();

        maleUser = new UserDTO()
                .setDateOfBirth(LocalDate.of(LocalDate.now().getYear() - 24,
                        12, 31))
                .setWeightInKgs(70)
                .setHeightInCms(175)
                .setSex(Sex.MALE)
                .setPal(PhysicalActivityLevel.VIGOROUSLY_ACTIVE)
                .setDietGoal(DietGoal.MUSCLE_GAIN);

        femaleUser = new UserDTO()
                .setDateOfBirth(LocalDate.of(LocalDate.now().getYear() - 20,
                        12, 31))
                .setWeightInKgs(73)
                .setHeightInCms(164)
                .setSex(Sex.FEMALE)
                .setPal(PhysicalActivityLevel.SEDENTARY)
                .setDietGoal(DietGoal.FAT_LOSS);
    }

    @Test
    void shouldCalculateAge() {
        //when
        int age = calculationsService.calculateAge(maleUser);
        //then
        assertThat(age).isEqualTo(23);
    }

    @Test
    void shouldCalculateAndSetBasalMetabolicRateForMale() {
        //when
        int bmr = calculationsService.calculateAndSetBasalMetabolicRate(maleUser);
        //then
        assertThat(maleUser.getBmr())
                .isNotZero()
                .isEqualTo(bmr);
        assertThat(bmr).isEqualTo(1684);
    }

    @Test
    void shouldCalculateAndSetBasalMetabolicRateForFemale() {
        //when
        int bmr = calculationsService.calculateAndSetBasalMetabolicRate(femaleUser);
        //then
        assertThat(femaleUser.getBmr())
                .isNotZero()
                .isEqualTo(bmr);
        assertThat(bmr).isEqualTo(1499);
    }

    @Test
    void shouldCalculateTotalDailyEnergyExpenditure() {
        //when
        double tdee = calculationsService.calculateTotalDailyEnergyExpenditure(femaleUser);
        //then
        assertThat(tdee).isEqualTo(2098.6);
    }

    @Test
    void shouldCalculateAndSetCaloriesToEatDaily() {
        //when
        int caloriesToEatDaily = calculationsService.calculateAndSetCaloriesToEatDaily(maleUser);
        //then
        assertThat(maleUser.getCaloriesToEatDaily())
                .isNotZero()
                .isEqualTo(caloriesToEatDaily);
        assertThat(caloriesToEatDaily).isEqualTo(3242);
    }
}