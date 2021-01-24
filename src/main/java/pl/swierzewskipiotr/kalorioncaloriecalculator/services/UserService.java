package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

    public Integer addNewUser(UserDTO userDTO, OAuth2AuthenticationToken authentication) {
        final UserEntity userEntity = new UserEntity();
        Integer githubId = authentication.getPrincipal().getAttribute("id");
        userEntity.setGithubId(githubId);
        String name = authentication.getPrincipal().getAttribute("name");
        if (name == null) {
            name = authentication.getPrincipal().getAttribute("login");
        }
        userEntity.setUserName(name);
        userEntity.setSex(userDTO.getSex());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setHeightInCms(userDTO.getHeightInCms());
        userEntity.setWeightInKgs(userDTO.getWeightInKgs());
        userEntity.setBmr(userDTO.getBMR());
        userEntity.setCalculatedCaloricIntake(userDTO.getTDEEmodifiedByDietGoal());
        UserEntity savedUser = userRepository.save(userEntity);
        return savedUser.getGithubId();
    }

    public int getBMRbyUserId(Integer userId) {
        final Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("No user of id: " + userId);
        }
        UserDTO userDTO = userMapper.toDTO(userEntityOptional.get());
        return userDTO.getBMR();
    }

    public int getCalculatedCaloriesByUserId(Integer userId) {
        final Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("No user of id: " + userId);
        }
        UserDTO userDTO = userMapper.toDTO(userEntityOptional.get());
        return userDTO.getCalculatedCalorieIntake();
    }
}
