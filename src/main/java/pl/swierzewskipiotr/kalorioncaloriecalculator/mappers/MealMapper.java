package pl.swierzewskipiotr.kalorioncaloriecalculator.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.MealDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.MealEntity;

@RequiredArgsConstructor
@Component
public class MealMapper {

    public MealDTO toDTO(MealEntity entity) {
        final MealDTO dto = new MealDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setKcal(entity.getKcal());
        dto.setProtein(entity.getProtein());
        dto.setFat(entity.getFat());
        dto.setCarb(entity.getCarb());
        dto.setWeight(entity.getWeight());

        return dto;
    }

    public MealEntity toEntity(MealDTO dto) {
        final MealEntity entity = new MealEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setKcal(dto.getKcal());
        entity.setProtein(dto.getProtein());
        entity.setFat(dto.getFat());
        entity.setCarb(dto.getCarb());
        entity.setCarb(dto.getCarb());

        return entity;
    }
}



