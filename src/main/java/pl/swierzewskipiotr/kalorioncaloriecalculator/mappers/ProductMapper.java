package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.ProductDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.ProductEntity;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDTO toDTO(ProductEntity entity) {
        final ProductDTO dto = new ProductDTO();

        dto.setName(entity.getName());
        dto.setProteinsPer100g(entity.getProteinsPer100g());
        dto.setFatsPer100g(entity.getFatsPer100g());
        dto.setCarbsPer100g(entity.getCarbsPer100g());

        return dto;
    }

    public ProductEntity toEntity(ProductDTO dto) {
        final ProductEntity entity = new ProductEntity();
        entity.setName(dto.getName());
        entity.setProteinsPer100g(dto.getProteinsPer100g());
        entity.setFatsPer100g(dto.getFatsPer100g());
        entity.setCarbsPer100g(dto.getCarbsPer100g());

        return entity;
    }
}
