package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName (String userName);
}
