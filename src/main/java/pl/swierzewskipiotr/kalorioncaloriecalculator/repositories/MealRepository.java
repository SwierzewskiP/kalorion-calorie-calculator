package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Meal;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;
import pl.swierzewskipiotr.kalorioncaloriecalculator.enums.MealType;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findMealEntitiesByUserAndDateAndMealType(User user, LocalDate date, MealType mealType);
}
