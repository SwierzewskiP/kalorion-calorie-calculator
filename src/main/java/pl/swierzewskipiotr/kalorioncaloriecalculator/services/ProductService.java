package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.dtos.ProductDTO;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.Product;
import pl.swierzewskipiotr.kalorioncaloriecalculator.mappers.ProductMapper;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        final List<Product> allEntities = productRepository.findAll();

        final List<ProductDTO> dtosList = allEntities.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        return dtosList;
    }

}
