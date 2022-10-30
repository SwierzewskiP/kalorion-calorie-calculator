package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.ProductDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDTO toDTO(Product entity) {
        final ProductDTO dto = new ProductDTO();
        dto.setName(entity.getName());
        dto.setMacroPer100g(entity.getMacroPer100g());

        return dto;
    }

    public Product toEntity(ProductDTO dto) {
        final Product entity = new Product();
        entity.setName(dto.getName());
        entity.setMacroPer100g(dto.getMacroPer100g());

        return entity;
    }
}
