package pl.swierzewskipiotr.kalorioncaloriecalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductEntity productEntity;
    private int weight;
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
}
