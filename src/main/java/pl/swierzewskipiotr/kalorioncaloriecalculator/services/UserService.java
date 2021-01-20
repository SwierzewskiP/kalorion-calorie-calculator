package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.mappers.UserMapper;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long addNewUser(UserDTO userDTO) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setSex(userDTO.getSex());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setHeightInCms(userDTO.getHeightInCms());
        userEntity.setWeightInKgs(userDTO.getWeightInKgs());
        userEntity.setBmr(userDTO.getBMR());
        userEntity.setCalculatedCaloricIntake(userDTO.getTDEEmodifiedByDietGoal());
        UserEntity savedUser = userRepository.save(userEntity);
        return savedUser.getId();
    }

    public int getBMRbyUserId(Long userId) {
        final Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("Nie znaleziono użytkownika: " + userId);
        }
        UserDTO userDTO = userMapper.toDTO(userEntityOptional.get());
        return userDTO.getBMR();
    }

    public int getCalculatedCaloriesByUserId(Long userId) {
        final Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("No user of id: " + userId);
        }
        UserDTO userDTO = userMapper.toDTO(userEntityOptional.get());
        return userDTO.getCalculatedCalorieIntake();
    }
}
