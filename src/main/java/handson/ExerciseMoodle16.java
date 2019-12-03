package handson;

import handson.impl.CartService;
import handson.impl.CustomerService;
import handson.impl.ProductQueryService;
import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import io.sphere.sdk.customers.queries.CustomerByKeyGet;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.queries.PagedQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


/**
 * Create a cart for a customer and add a product to it.
 *
 * See:
 *  TODO Task16.1 {@link ProductQueryService#findCategory(Locale, String)}
 *  TODO Task16.2 {@link ProductQueryService#withCategory(Category)}
 *  TODO Task16.3 {@link ProductQueryService#findProductsWithCategory(Locale, String)}
 */
public class ExerciseMoodle16 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle16.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);
            final CartService cartService = new CartService(client);
            final ProductQueryService productQueryService = new ProductQueryService(client);


            final CompletionStage<PagedQueryResult<ProductProjection>> productsOnSaleResult
                    = productQueryService.findProductsWithCategory(Locale.US, "summer");

            final Cart cart = client.execute(CustomerByIdGet.of("a0709e07-42ff-4554-9c23-9faf855b4c01"))
                    .thenComposeAsync(cartService::createCart)
                    .thenCombineAsync(productsOnSaleResult, (c, p) -> cartService.addProductToCartCommand(p.getResults().get(0), c))
                    .thenComposeAsync(client::execute)
                    .toCompletableFuture().get();

            LOG.info("Updated Cart {}", cart);
        }
    }
}
