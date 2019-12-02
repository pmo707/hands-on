package handson;

import handson.impl.CustomerService;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryByKeyGet;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductUpdateCommand;
import io.sphere.sdk.products.commands.updateactions.AddToCategory;
import io.sphere.sdk.products.queries.ProductByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;



public class ExerciseMoodle11 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle11.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            // Day 1

            // Get your category

            final Category category = null;
            LOG.info("My category {}", category);

            // Get your product

            final Product product = null;
            LOG.info("My product {}", product);


            // Day 2

            final Product productUpdated = null;

            LOG.info("My product with new category {}", productUpdated);

        }
    }
}
