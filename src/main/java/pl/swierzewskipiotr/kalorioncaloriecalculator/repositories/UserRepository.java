package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUserName (String userName);
}
