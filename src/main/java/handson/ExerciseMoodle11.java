package handson;

import handson.impl.CustomerService;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryByIdGet;
import io.sphere.sdk.categories.queries.CategoryByKeyGet;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.commands.ProductUpdateCommand;
import io.sphere.sdk.products.commands.updateactions.AddToCategory;
import io.sphere.sdk.products.queries.ProductByIdGet;
import io.sphere.sdk.products.queries.ProductByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle11 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle11.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            // Day 1

            // Get your category

            final Category category = client.execute(CategoryByIdGet.of("e292066b-d8a4-4dc8-b29e-46192412e5e6")).toCompletableFuture().get();
            LOG.info("My category {}", category);

            // Get your product

            final Product product = client.execute(ProductByIdGet.of("edd4d7ea-fe38-41f1-862f-ddcab6227ff2")).toCompletableFuture().get();
            LOG.info("My product {}", product);


            // Day 2

            ProductUpdateCommand productUpdateCommand = ProductUpdateCommand.of(product, AddToCategory.of(category));
            final Product productUpdated = client.execute(productUpdateCommand).toCompletableFuture().get();
            LOG.info("My product with new category {}", productUpdated);

        }
    }
}
