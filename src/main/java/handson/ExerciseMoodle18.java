package handson;

import handson.impl.CartService;
import handson.impl.CustomerService;
import handson.impl.OrderService;
import handson.impl.ProductQueryService;
import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.queries.CustomerByKeyGet;
import io.sphere.sdk.orders.Order;
import io.sphere.sdk.orders.OrderState;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


/**
 * Create a cart for a customer, add a product to it, create an order from the cart and change the order state.
 *
 * See:
 *  TODO Task18.1 {@link CartService#addDiscountToCart(String, Cart)}}
 */
public class ExerciseMoodle18 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle18.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);
            final CartService cartService = new CartService(client);
            final ProductQueryService productQueryService = new ProductQueryService(client);
            final OrderService orderService = new OrderService(client);


            final CompletionStage<ProductProjection> productProjectionCompletionStage
                    = client.execute(ProductProjectionByKeyGet.ofCurrent("red-wine"));

            // Create and update an order
            // Use a customer you can get by key
            // Add a discount code to it
            final Cart cartWithCode = null;

            LOG.info("Created order {}", cartWithCode);


        }
    }
}
