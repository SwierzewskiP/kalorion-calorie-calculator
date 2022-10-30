package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDTO(User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSex(user.getSex());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setHeightInCms(user.getHeightInCms());
        userDTO.setWeightInKgs(user.getWeightInKgs());
        userDTO.setBmr(user.getBmr());
        userDTO.setCaloriesToEatDaily(user.getCaloriesToEatDaily());

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        final User user = new User();
        user.setName(userDTO.getName());
        user.setSex(userDTO.getSex());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setHeightInCms(userDTO.getHeightInCms());
        user.setWeightInKgs(userDTO.getWeightInKgs());
        user.setBmr(userDTO.getBmr());
        user.setCaloriesToEatDaily(userDTO.getCaloriesToEatDaily());

        return user;
    }
}
