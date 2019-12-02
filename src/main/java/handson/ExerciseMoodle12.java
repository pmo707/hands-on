package handson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import handson.impl.CustomerService;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.queries.CategoryByIdGet;
import io.sphere.sdk.categories.queries.CategoryByKeyGet;
import io.sphere.sdk.categories.queries.CategoryQuery;
import io.sphere.sdk.categories.queries.CategoryQueryModel;
import io.sphere.sdk.client.JsonNodeSphereRequest;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.http.HttpMethod;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.queries.ProductByKeyGet;
import io.sphere.sdk.queries.QueryDsl;
import io.sphere.sdk.queries.QueryModel;
import io.sphere.sdk.queries.QueryPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle12 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle12.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {


            final JsonNode categoriesFromName = client.execute(JsonNodeSphereRequest
                    .of(HttpMethod.GET, "/categories?where=name%28en-US%3D%22summer%22%29", null))
                    .toCompletableFuture()
                    .get();

            LOG.info("JSON response: {}", categoriesFromName);


//            QueryPredicate<Category> predicate = QueryPredicate.of("assets(sources(uri=\"https://www.commercetools.de/ct-logo.svg\"))");
//            CategoryQuery categoryQuery = CategoryQuery.of().withPredicates(predicate);
//            //   System.out.println(categoryQuery);
            final JsonNode categoriesFromImage = client.execute(JsonNodeSphereRequest
                    .of(HttpMethod.GET, "/categories?where=assets%28sources%28uri%3D%22https%3A%2F%2Fwww.commercetools.de%2Fct-logo.svg%22%29%29", null))
                    .toCompletableFuture()
                    .get();

            LOG.info("Those categories are found: {}", categoriesFromImage);

        }
    }
}
