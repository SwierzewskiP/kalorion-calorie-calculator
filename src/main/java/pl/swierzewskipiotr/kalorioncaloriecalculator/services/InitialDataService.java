package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Macro;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Meal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.Sex;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.ProductRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Profile("!prod")
public class InitialDataService implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {
        fillSampleUserWithMealsForToday();
    }

    void fillSampleUserWithMealsForToday() {

        final User user1 = new User()
                .setId(65499866)
                .setBmr(1645)
                .setCaloriesToEatDaily(2533)
                .setDateOfBirth(LocalDate.of(1988, 12, 14))
                .setHeightInCms(180)
                .setName("Andrzej")
                .setSex(Sex.MALE)
                .setWeightInKgs(68);

        final Macro macroOfBreadPer100g = new Macro(241, 6.9, 1.1, 49);
        final Product bread = new Product(null, "Oskroba Chleb Baltonowski 500g", macroOfBreadPer100g);
        productRepository.save(bread);
        final Meal breadAsMeal = new Meal(null, bread, macroOfBreadPer100g, 100, user1, MealType.BREAKFAST, LocalDate.now());

        final Macro macroOfYogurtPer100g = new Macro(63, 4.2, 2.5, 5.8);
        final Product yogurt = new Product(null, "Jogurt Naturalny (Kesem)", macroOfYogurtPer100g);
        productRepository.save(yogurt);
        final Meal yogurtAsMeal = new Meal(null, yogurt, macroOfYogurtPer100g, 100, user1, MealType.BRUNCH, LocalDate.now());

        final Macro macroOfEggPer100g = new Macro(139, 12.5, 9.7, 0.6);
        final Product egg = new Product(null, "Jajo kurze rozmiar L (Lidl)", macroOfEggPer100g);
        productRepository.save(egg);
        final Meal eggAsMeal = new Meal(null, egg, macroOfEggPer100g, 100, user1, MealType.LUNCH, LocalDate.now());

        final Macro macroOfBriePer100g = new Macro(360, 17, 32, 1);
        final Product brie = new Product(null, "Ser Brie", macroOfBriePer100g);
        productRepository.save(brie);
        final Meal brieAsMeal = new Meal(null, brie, macroOfBriePer100g, 100, user1, MealType.DINNER, LocalDate.now());

        final Macro macroOfTomatoPer100g = new Macro(19, 0.9, 0.2, 4.1);
        final Product tomato = new Product(null, "Pomidor", macroOfTomatoPer100g);
        productRepository.save(tomato);
        final Meal tomatoAsMeal = new Meal(null, tomato, macroOfTomatoPer100g, 100, user1, MealType.DINNER, LocalDate.now());

        final Meal breadAsMeal2 = new Meal(null, bread, macroOfBreadPer100g, 100, user1, MealType.SUPPER, LocalDate.now());

        final Meal brieAsMeal2 = new Meal(null, brie, macroOfBriePer100g, 100, user1, MealType.BRUNCH, LocalDate.now().minusDays(1));

        final Meal eggAsMeal2 = new Meal(null, egg, macroOfEggPer100g, 100, user1, MealType.BRUNCH, LocalDate.now().minusDays(1));

        user1.getMeals().addAll(List.of(breadAsMeal, yogurtAsMeal, eggAsMeal, brieAsMeal, tomatoAsMeal, breadAsMeal2,
                brieAsMeal2, eggAsMeal2));
        userRepository.save(user1);

        log.info("Dodano przykładowego użytkownika " + user1.getName() + " wraz z posiłkami na dziś: " + user1.getMeals());
    }
}