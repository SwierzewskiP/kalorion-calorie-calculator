package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {
}
