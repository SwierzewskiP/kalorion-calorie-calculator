package pl.swierzewskipiotr.kalorioncaloriecalculator.services;

import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.services.FatsecretService;
import com.fatsecret.platform.services.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class StartupService implements CommandLineRunner {

    @Value("${fatsecret-platform.key}")
    private String key;

    @Value("${fatsecret-platform.secret}")
    private String secret;

    @Override
    public void run(String... args) throws Exception {
            FatsecretService service = new FatsecretService(key, secret);

            String query = "pasta"; //Your query string
            Response<CompactFood> response = service.searchFoods(query);
            //This response contains the list of food items at zeroth page for your query

            List<CompactFood> results = response.getResults();
            //This list contains summary information about the food items

        for (CompactFood result : results) {
            String pattern = "Per ([0-9]+)g - Calories: ([0-9]+)kcal \\| Fat: ([0-9\\.]+)g \\| Carbs: ([0-9\\.]+)g \\| Protein: ([0-9\\.]+)g";
            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);
            // Now create matcher object
            System.out.println(result.getDescription());
            Matcher m = r.matcher(result.getDescription());
            m.find();
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
            System.out.println("Found value: " + m.group(4) );
        }


        Response<CompactFood> responseAtPage3 = service.searchFoods(query, 3);
            //This response contains the list of food items at page number 3 for your query
            //If total results are less, then this response will have empty list of the food items
        }
    }
