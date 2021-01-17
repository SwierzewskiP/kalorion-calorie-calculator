package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.swierzewskipiotr.kalorioncaloriecalculator.entities.ProductEntity;
import pl.swierzewskipiotr.kalorioncaloriecalculator.repositories.ProductRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Profile("!prod")
public class InitialDataService implements CommandLineRunner {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        fillSampleProducts();
    }

    void fillSampleProducts() {
        log.info("Wypełniam bazę danych przykładowymi produktami...");

        final ProductEntity chlebBaltonowski = new ProductEntity(null, "Oskroba Chleb Baltonowski 500g",
                241, 6.9, 1.1, 49.0);
        final ProductEntity jogurtNaturalny = new ProductEntity(null, "Jogurt Naturalny (Kesem)",
                63, 4.2, 2.5, 5.8);
        final ProductEntity jajoKurzeL = new ProductEntity(null, "Jajo kurze rozmiar L (Lidl)",
                139, 12.5, 9.7, 0.6);
        final ProductEntity serBrie = new ProductEntity(null, "Ser Brie", 360,
                17, 32, 1);
        final ProductEntity pomidor = new ProductEntity(null, "Pomidor", 19, 0.9,
                0.2, 4.1);

        productRepository.save(chlebBaltonowski);
        productRepository.save(jogurtNaturalny);
        productRepository.save(jajoKurzeL);
        productRepository.save(serBrie);
        productRepository.save(pomidor);
    }
}
