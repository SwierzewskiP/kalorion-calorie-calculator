package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.HelloService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HelloService helloService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("greeting", helloService.fetchHelloMessage());

        return "index";
    }
}
