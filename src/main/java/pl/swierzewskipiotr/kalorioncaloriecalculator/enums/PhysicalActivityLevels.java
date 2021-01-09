package pl.swierzewskipiotr.kalorioncaloriecalculator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhysicalActivityLevels {
    EXTREMELY_INACTIVE(1.2),
    SEDENTARY(1.4),
    MODERATELY_ACTIVE(1.7),
    VIGOROUSLY_ACTIVE(2.0),
    EXTREMELY_ACTIVE(2.4);

    private final double displayNumber;
}
