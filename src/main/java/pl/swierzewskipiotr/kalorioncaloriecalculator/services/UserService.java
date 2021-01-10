package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addNewUser(UserDTO userDTO) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setSex(userDTO.getSex());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setHeightInCms(userDTO.getHeightInCms());
        userEntity.setWeightInKgs(userDTO.getWeightInKgs());
        userEntity.setCalculatedCaloricIntake(userDTO.getTDEE());
        userRepository.save(userEntity);
    }
}
