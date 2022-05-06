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

    public void addNewUser(UserDTO userDTO, OAuth2AuthenticationToken authentication) {
        final UserEntity userEntity = new UserEntity();
        Integer githubId = authentication.getPrincipal().getAttribute("id");
        userEntity.setGithubId(githubId);
        userEntity.setName(userDTO.getName());
        userEntity.setSex(userDTO.getSex());
        userEntity.setDateOfBirth(userDTO.getDateOfBirth());
        userEntity.setHeightInCms(userDTO.getHeightInCms());
        userEntity.setWeightInKgs(userDTO.getWeightInKgs());
        userEntity.setBmr(userDTO.getBMR());
        userEntity.setCalculatedCaloricIntake(userDTO.getTDEEmodifiedByDietGoal());

        userRepository.save(userEntity);
    }

    public String getNameByUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));

        return userDTO.getName();
    }

    public int getBMRbyUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));
        return userDTO.getBMR();
    }

    public int getCalculatedCaloriesByUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));
        return userDTO.getCalculatedCalorieIntake();
    }

    private UserEntity getUserEntity(Integer userId) {
        final Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("No user of id: " + userId);
        }
        return userEntityOptional.get();
    }
}
