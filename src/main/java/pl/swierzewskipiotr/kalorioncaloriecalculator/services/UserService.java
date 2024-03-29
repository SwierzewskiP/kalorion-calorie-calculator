package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.UserDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.mappers.UserMapper;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCalculationsService userCalculationsService;
    private final UserMapper userMapper;

    public void addNewUser(UserDTO userDTO, OAuth2AuthenticationToken authentication) {
        userCalculationsService.calculateAndSetBasalMetabolicRate(userDTO);
        userCalculationsService.calculateAndSetCaloriesToEatDaily(userDTO);
        Integer githubId = authentication.getPrincipal().getAttribute("id");
        User user = userMapper.toEntity(userDTO);
        user.setId(githubId);

        userRepository.save(user);
        log.info("Pomyślnie dodano użytkownika z GitHub id: " + githubId);
    }

    public String getNameByUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));

        return userDTO.getName();
    }

    public int getBMRbyUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));
        return userDTO.getBmr();
    }

    public int getCalculatedCaloriesByUserId(Integer userId) {
        UserDTO userDTO = userMapper.toDTO(getUserEntity(userId));
        return userDTO.getCaloriesToEatDaily();
    }

    public User getUserEntity(Integer userId) {
        final Optional<User> userEntityOptional = userRepository.findById(userId);
        if (userEntityOptional.isEmpty()) {
            throw new RuntimeException("No user of id: " + userId);
        }
        return userEntityOptional.get();
    }

    public boolean existsById(Integer userId) {
        return userRepository.existsById(userId);
    }
}

