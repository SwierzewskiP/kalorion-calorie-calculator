package pl.swierzewskipiotr.kalorioncaloriecalculator.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.HelloService;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {

    private final HelloService helloService;

    @GetMapping(value = "/hello", produces = "text/plain")
    public String hello() {
        return helloService.fetchHelloMessage();
    }
}
