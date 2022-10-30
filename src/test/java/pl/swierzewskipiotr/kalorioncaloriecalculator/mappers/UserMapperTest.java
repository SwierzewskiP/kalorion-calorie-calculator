package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private UserMapper mapper;
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        //given
        mapper = new UserMapper();

        userDTO = new UserDTO()
                .setName("Andrzej")
                .setSex(Sex.MALE)
                .setDateOfBirth(LocalDate.of(2000, 1, 1))
                .setHeightInCms(175)
                .setWeightInKgs(70)
                .setBmr(1700)
                .setCaloriesToEatDaily(3000);

        user = new User()
                .setName("Julia")
                .setSex(Sex.FEMALE)
                .setDateOfBirth(LocalDate.of(2000, 12, 12))
                .setHeightInCms(165)
                .setWeightInKgs(75)
                .setBmr(1400)
                .setCaloriesToEatDaily(1800);
    }

    @Test
    void shouldMapEntityToDTO() {
        //when
        UserDTO returnedDTO = mapper.toDTO(user);
        //then
        assertThat(returnedDTO)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", user.getName())
                .hasFieldOrPropertyWithValue("sex", user.getSex())
                .hasFieldOrPropertyWithValue("dateOfBirth", user.getDateOfBirth())
                .hasFieldOrPropertyWithValue("heightInCms", user.getHeightInCms())
                .hasFieldOrPropertyWithValue("weightInKgs", user.getWeightInKgs())
                .hasFieldOrPropertyWithValue("bmr", user.getBmr())
                .hasFieldOrPropertyWithValue("caloriesToEatDaily", user.getCaloriesToEatDaily());
    }

    @Test
    void shouldMapDTOtoEntity() {
        //when
        User returnedEntity = mapper.toEntity(userDTO);
        //then
        assertThat(returnedEntity)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", userDTO.getName())
                .hasFieldOrPropertyWithValue("sex", userDTO.getSex())
                .hasFieldOrPropertyWithValue("dateOfBirth", userDTO.getDateOfBirth())
                .hasFieldOrPropertyWithValue("heightInCms", userDTO.getHeightInCms())
                .hasFieldOrPropertyWithValue("weightInKgs", userDTO.getWeightInKgs())
                .hasFieldOrPropertyWithValue("bmr", userDTO.getBmr())
                .hasFieldOrPropertyWithValue("caloriesToEatDaily", userDTO.getCaloriesToEatDaily());
    }
}