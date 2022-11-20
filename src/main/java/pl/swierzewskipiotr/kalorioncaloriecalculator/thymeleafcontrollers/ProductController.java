package pl.swierzewskipiotr.kalorioncaloriecalculator.thymeleafcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.ProductDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.services.ProductService;

import javax.validation.Valid;
import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/fooddiary/{date}/product/edit/{id}")
    public String getProductToEdit(@PathVariable LocalDate date, @PathVariable("id") Long id, Model model) {
        ProductDTO productDTO = productService.getProductDTObyId(id);

        model.addAttribute("productDTO", productDTO);

        return "editproduct";
    }

    @PostMapping("/fooddiary/{date}/product/update/{id}")
    public String postProductToUpdate(@PathVariable LocalDate date, @PathVariable("id") Long id,
                                      @Valid ProductDTO productDTO, BindingResult result) {
        ProductDTO productTemp = productService.getProductDTObyId(id);
        if (result.hasErrors()) {
            productDTO.setName(productTemp.getName());

            return "editproduct";
        }
        productService.updateProduct(productDTO);

        return "redirect:/fooddiary/" + date;
    }
}
