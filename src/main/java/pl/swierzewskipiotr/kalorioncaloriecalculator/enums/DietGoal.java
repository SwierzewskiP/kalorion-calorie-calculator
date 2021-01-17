package pl.swierzewskipiotr.kalorioncaloriecalculator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  DietGoal {
        FAT_LOSS(0.85, "Redukcja"),
        MAINTENANCE(1.0, "Utrzymanie"),
        MUSCLE_GAIN(1.1,"Masa");

        private final double value;
        private final String description;
    }
