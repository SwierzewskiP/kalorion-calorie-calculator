package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.MealDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Meal;

@RequiredArgsConstructor
@Component
public class MealMapper {

    public MealDTO toDTO(Meal entity) {
        final MealDTO dto = new MealDTO();
        dto.setId(entity.getId());
        dto.setProduct(entity.getProduct());
        dto.setMacro(entity.getMacro());
        dto.setWeight(entity.getWeight());

        return dto;
    }

    public Meal toEntity(MealDTO dto) {
        final Meal entity = new Meal();
        entity.setId(dto.getId());
        entity.setProduct(dto.getProduct());
        entity.setMacro(dto.getMacro());
        entity.setWeight(dto.getWeight());

        return entity;
    }
}



