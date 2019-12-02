package handson;

import handson.impl.CartService;
import handson.impl.CustomerService;
import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.commands.CustomerUpdateCommand;
import io.sphere.sdk.customers.commands.updateactions.SetKey;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import io.sphere.sdk.customers.queries.CustomerByKeyGet;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


/**
 * Create a cart for a customer.
 *
 * See:
 *  TODO Task15.1 {@link CartService#createCart(Customer)}
 *  TODO Task15.2 {@link CartService#addProductToCart(ProductProjection, Cart)}
 */
public class ExerciseMoodle15 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle15.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);
            final CartService cartService = new CartService(client);


            // Do alone before executing the lines below !
            // Add a key to your customer!


            final Customer customerNew = null;

            LOG.info("Customer Updated {}", customerNew);


            final ProductProjection productProjection = client.execute(ProductProjectionByKeyGet.ofCurrent("red-wine"))
                    .toCompletableFuture().get();

            final Cart cartUpdated = client.execute(CustomerByKeyGet.of("YOUR-CUSTOMER-KEY"))
                    .thenComposeAsync(cartService::createCart)
                    .thenComposeAsync(cartNew -> cartService.addProductToCart(productProjection, cartNew))
                    .toCompletableFuture()
                    .get();

            LOG.info("Cart Updated {}", cartUpdated);

        }
    }
}
