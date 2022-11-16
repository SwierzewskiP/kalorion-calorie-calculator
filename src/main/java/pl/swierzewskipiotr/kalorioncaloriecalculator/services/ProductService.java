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

    public void updateProduct(ProductDTO productDTO) {
        Product product = getProductById(productDTO.getId());
        product.setMacroPer100g(productDTO.getMacroPer100g());
        productRepository.save(product);
    }

    public ProductDTO getProductDTObyId(Long id) {
        Product product = getProductById(id);
        return productMapper.toDTO(product);
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brak produktu o takim ID:" + id));
    }
}
