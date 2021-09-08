package pl.swierzewskipiotr.kalorioncaloriecalculator.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserDtoTest {

    private UserDTO maleDTO;
    private UserDTO femaleDTO;

    @BeforeEach
    void setUp() {
        maleDTO = new UserDTO();
        maleDTO.setSex(Sex.MALE);
        maleDTO.setDateOfBirth(LocalDate.of(1987, 11, 3));
        maleDTO.setWeightInKgs(75);
        maleDTO.setHeightInCms(175);

        femaleDTO = new UserDTO();
        femaleDTO.setSex(Sex.FEMALE);
        femaleDTO.setDateOfBirth(LocalDate.of(1987, 11, 3));
        femaleDTO.setWeightInKgs(60);
        femaleDTO.setHeightInCms(165);
    }

    @Test
    void shouldGetMaleBMR() {
        //given
        //when
        int result = maleDTO.getBMR();

        //then
        assertThat(result).isEqualTo(1678);
    }

    @Test
    void shouldGetFemaleBMR() {
        //given
        //when
        int result = femaleDTO.getBMR();

        //then
        assertThat(result).isEqualTo(1300);
    }

    @Test
    void shouldGetUserTDEE() {
    }

    @Test
    void shouldGetTDEEmodifiedByDietGoal() {
    }

    @Test
    void shouldGetCalculatedCalorieIntake() {
    }
}