package pl.swierzewskipiotr.kalorioncaloriecalculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
