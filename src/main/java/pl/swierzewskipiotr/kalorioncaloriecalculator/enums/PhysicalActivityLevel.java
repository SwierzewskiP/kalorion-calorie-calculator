package pl.swierzewskipiotr.kalorioncaloriecalculator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PhysicalActivityLevel {
    INACTIVE(1.2, "Prawie brak aktywności fizycznej: praca siedząca/uczeń, brak regularnych ćwiczeń, niska aktywność fizyczna w ciągu dnia (do 5 tys. kroków)"),
    SEDENTARY(1.4,
            "Siedzący tryb życia: praca siedząca/uczeń, treningi (np. na siłowni lub w domu) 2-4x w tygodniu, umiarkowana aktywność fizyczna w ciągu dnia (ok.5-9 tysięcy kroków dziennie)"),
    MODERATELY_ACTIVE(1.6,"Średnia aktywność fizyczna: praca fizyczna lub 3-6 treningów w tygodniu o wysokiej intensywności ( bieganie, jazda na rowerze, trening siłowy), codzienna aktywność na poziomie ponad 10 tys. kroków"),
    VIGOROUSLY_ACTIVE(1.75, "Wysoka aktywność fizyczna: praca fizyczna lub codzienne treningi o wysokiej intensywności, dzienna aktywność na poziomie ponad 15 tys. kroków"),
    EXTREMELY_ACTIVE(2.0, "Bardzo wysoka aktywność fizyczna: wymagająca praca fizyczna, codzienne treningi o wysokiej intensywności, ponad 20 tys. kroków dziennie");

    private final double value;
    private final String description;
}

