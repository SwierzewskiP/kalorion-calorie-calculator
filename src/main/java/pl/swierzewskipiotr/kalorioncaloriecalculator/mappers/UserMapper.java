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
        userDTO.setName(userEntity.getName());
        userDTO.setSex(userEntity.getSex());
        userDTO.setDateOfBirth(userEntity.getDateOfBirth());
        userDTO.setHeightInCms(userEntity.getHeightInCms());
        userDTO.setWeightInKgs(userEntity.getWeightInKgs());
        userDTO.setBmr(userEntity.getBmr());
        userDTO.setCaloriesToEatDaily(userEntity.getCaloriesToEatDaily());

        return userDTO;
    }

    public UserEntity toEntity(UserDTO userDTO) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setSex(userDTO.getSex());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setHeightInCms(userDTO.getHeightInCms());
        userEntity.setWeightInKgs(userDTO.getWeightInKgs());
        userEntity.setBmr(userDTO.getBmr());
        userEntity.setCaloriesToEatDaily(userDTO.getCaloriesToEatDaily());

        return userEntity;
    }
}
