package pl.swierzewskipiotr.kalorioncaloriecalculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MealType {
    BREAKFAST("Śniadanie"),
    BRUNCH("Drugie śniadanie / brunch"),
    LUNCH("Lunch"),
    DINNER("Obiad"),
    SUPPER("Kolacja");

    private final String description;
}