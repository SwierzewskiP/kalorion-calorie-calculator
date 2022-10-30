package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.User;

public interface UserRepository  extends JpaRepository<User, Integer> {
}
