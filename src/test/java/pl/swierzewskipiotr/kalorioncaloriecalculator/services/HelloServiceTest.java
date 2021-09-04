package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloServiceTest {

    @Test
    void fetchHelloMessage() {
        //given
        HelloService helloService = new HelloService(0);

        //when
        String result = helloService.fetchHelloMessage();

        //then
        assertEquals(result, "Witaj użytkowniku! Jesteś " + 1 + " chcącym liczyć z nami kalorie!");

    }
}