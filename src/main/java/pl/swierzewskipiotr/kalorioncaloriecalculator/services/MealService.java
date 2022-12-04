package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.MealDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Meal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;
import pl.swierzewskipiotr.kalorioncaloriecalculator.mappers.MealMapper;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.MealRepository;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MealMapper mealMapper;
    private final UserService userService;

    public void addMealWithNewProduct(MealDTO mealDTO, OAuth2AuthenticationToken authentication, LocalDate date, MealType mealType) {
        User user = userService.getUserEntity(authentication.getPrincipal().getAttribute("id"));
        mealDTO.setUser(user);
        mealDTO.setMealType(mealType);
        mealDTO.setDate(date);
        Meal meal = mealMapper.toEntity(mealDTO);
        String mealName = mealDTO.getProduct().getName();

        mealRepository.save(meal);
        log.info("Dodano nowy produkt do bazy: " + mealName + ".");
        log.info("Dodano nowy posiłek: " + mealName + " do: " + mealType.getDescription() + " " + date + ".");
    }

    public List<MealDTO> getAllMealsByUserAndDateAndMealType(User user, LocalDate date, MealType type) {
        final List<Meal> allEntities = mealRepository.findMealEntitiesByUserAndDateAndMealType(user, date, type);

        return allEntities.stream()
                .map(mealMapper::toDTO)
                .collect(toList());
    }

    public EnumMap<MealType, List<MealDTO>> getAllMealsForGivenDay(User user, LocalDate date) {
    final EnumMap<MealType, List<MealDTO>> allMeals = new EnumMap<>(MealType.class);
        for (MealType type : MealType.values()) {
            final List<MealDTO> allMealsOfType = getAllMealsByUserAndDateAndMealType(user, date, type);
            allMeals.put(type, allMealsOfType);
        }
        return allMeals;
    }

    public void updateMeal(MealDTO mealDTO) {
        Meal meal = getMealById(mealDTO.getId());
        meal.setWeight(mealDTO.getWeight());
        mealRepository.save(meal);
    }

    public void deleteMeal(Long id) {
        Meal meal = getMealById(id);
        mealRepository.delete(meal);
        log.info("Usunięto posiłek o ID: " + id);
    }

    public MealDTO getMealDTObyId(Long id) {
        Meal meal = getMealById(id);
        return mealMapper.toDTO(meal);
    }

    private Meal getMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brak posiłku o takim ID:" + id));
    }
}