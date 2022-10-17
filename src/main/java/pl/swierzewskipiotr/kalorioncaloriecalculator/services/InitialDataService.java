package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.MealEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.MealRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
@Profile("!prod")
public class InitialDataService implements CommandLineRunner {

    @Value("${fatsecret-platform.key}")
    private String key;

    @Value("${fatsecret-platform.secret}")
    private String secret;

    private final MealRepository mealRepository;

    private final UserRepository userRepository;
    private final UserEntity andrzej = new UserEntity();

    @Override
    @Transactional
    public void run(String... args) {
        fillSampleUser();
        fillSampleMeals();
    }

    void fillSampleUser() {
        andrzej.setGithubId(65499866)
                .setBmr(1645)
                .setCaloriesToEatDaily(2533)
                .setDateOfBirth(LocalDate.of(1988, 12, 14))
                .setHeightInCms(180)
                .setName("Andrzej")
                .setSex(Sex.MALE)
                .setWeightInKgs(68);

        userRepository.save(andrzej);
        log.info("Dodano przykładowego użytkownika: " + andrzej);
    }

    void fillSampleMeals() {
        log.info("Wypełniam bazę danych przykładowymi produktami...");

        final MealEntity chlebBaltonowski = new MealEntity()
                .setDate(LocalDate.now())
                .setMealType(MealType.BREAKFAST)
                .setName("Oskroba Chleb Baltonowski 500g")
                .setKcal(241)
                .setWeight(100)
                .setProtein(6.9)
                .setFat(1.1)
                .setCarb(49.0)
                .setUser(andrzej);

        final MealEntity jogurtNaturalny = new MealEntity()
                .setDate(LocalDate.now())
                .setMealType(MealType.BRUNCH)
                .setName("Jogurt Naturalny (Kesem)")
                .setKcal(63)
                .setWeight(100)
                .setProtein(4.2)
                .setFat(2.5)
                .setCarb(5.8)
                .setUser(andrzej);

        final MealEntity jajoKurzeL = new MealEntity()
                .setDate(LocalDate.now())
                .setMealType(MealType.LUNCH)
                .setName("Jajo kurze rozmiar L (Lidl)")
                .setKcal(139)
                .setWeight(100)
                .setProtein(12.5)
                .setFat(9.7)
                .setCarb(0.6)
                .setUser(andrzej);

        final MealEntity serBrie = new MealEntity()
                .setDate(LocalDate.now())
                .setMealType(MealType.DINNER)
                .setName("Ser Brie")
                .setKcal(360)
                .setWeight(100)
                .setProtein(17)
                .setFat(32)
                .setCarb(1)
                .setUser(andrzej);

        final MealEntity pomidor = new MealEntity()
                .setDate(LocalDate.now())
                .setMealType(MealType.DINNER)
                .setName("Pomidor")
                .setKcal(19)
                .setWeight(100)
                .setProtein(0.9)
                .setFat(0.2)
                .setCarb(4.1)
                .setUser(andrzej);

        mealRepository.save(chlebBaltonowski);
        mealRepository.save(jogurtNaturalny);
        mealRepository.save(jajoKurzeL);
        mealRepository.save(serBrie);
        mealRepository.save(pomidor);
    }
}
