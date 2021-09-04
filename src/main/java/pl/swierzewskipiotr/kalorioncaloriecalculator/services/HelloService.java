package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class HelloService {

    private int licznik = 0;

    public String fetchHelloMessage() {
        return "Witaj użytkowniku! Jesteś " + (++licznik) + " chcącym liczyć z nami kalorie!";
    }
}
