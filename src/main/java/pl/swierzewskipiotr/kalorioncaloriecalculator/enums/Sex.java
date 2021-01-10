package pl.swierzewskipiotr.kalorioncaloriecalculator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Sex {
    MALE("Mężczyzna"),
    FEMALE("Kobieta");

    private final String description;
}
