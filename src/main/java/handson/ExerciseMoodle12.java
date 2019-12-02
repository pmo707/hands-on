package handson;

import com.fasterxml.jackson.databind.JsonNode;
import handson.impl.CustomerService;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryByKeyGet;
import io.sphere.sdk.client.JsonNodeSphereRequest;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.http.HttpMethod;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.queries.ProductByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;



public class ExerciseMoodle12 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle12.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {


            final JsonNode categoriesFromName = null;

            LOG.info("JSON response: {}", categoriesFromName);


            final JsonNode categoriesFromImage = null;

            LOG.info("Those categories are found: {}", categoriesFromImage);

        }
    }
}
