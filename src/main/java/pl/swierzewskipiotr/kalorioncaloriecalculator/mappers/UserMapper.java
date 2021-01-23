package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;

    public UserDTO toDTO(UserEntity userEntity) {
        final UserDTO userDTO = new UserDTO();

//        userDTO.setUserName(userEntity.getUserName());
        userDTO.setSex(userEntity.getSex());
        userDTO.setDateOfBirth(userEntity.getDateOfBirth());
        userDTO.setHeightInCms(userEntity.getHeightInCms());
        userDTO.setWeightInKgs(userEntity.getWeightInKgs());
        userDTO.setCalculatedCalorieIntake(userEntity.getCalculatedCaloricIntake());

        return userDTO;
    }
}
