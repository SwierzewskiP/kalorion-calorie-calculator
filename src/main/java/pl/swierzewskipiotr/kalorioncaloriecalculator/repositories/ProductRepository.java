package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
