package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperTest {

    private UserMapper mapper;
    private UserDTO userDTO;
    private UserEntity userEntity;

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

        userEntity = new UserEntity()
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
        UserDTO returnedDTO = mapper.toDTO(userEntity);
        //then
        assertThat(returnedDTO)
                .isNotNull()
                .hasFieldOrPropertyWithValue("name", userEntity.getName())
                .hasFieldOrPropertyWithValue("sex", userEntity.getSex())
                .hasFieldOrPropertyWithValue("dateOfBirth", userEntity.getDateOfBirth())
                .hasFieldOrPropertyWithValue("heightInCms", userEntity.getHeightInCms())
                .hasFieldOrPropertyWithValue("weightInKgs", userEntity.getWeightInKgs())
                .hasFieldOrPropertyWithValue("bmr", userEntity.getBmr())
                .hasFieldOrPropertyWithValue("caloriesToEatDaily", userEntity.getCaloriesToEatDaily());
    }

    @Test
    void shouldMapDTOtoEntity() {
        //when
        UserEntity returnedEntity = mapper.toEntity(userDTO);
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