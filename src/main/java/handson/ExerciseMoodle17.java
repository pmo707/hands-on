package handson;

import handson.impl.CartService;
import handson.impl.CustomerService;
import handson.impl.OrderService;
import handson.impl.ProductQueryService;
import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import io.sphere.sdk.orders.Order;
import io.sphere.sdk.orders.OrderState;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionByKeyGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


/**
 * Create a cart for a customer, add a product to it, create an order from the cart and change the order state.
 * <p>
 * See:
 * TODO Task17.1 {@link OrderService#changeState(Order, OrderState)}
 * TODO Task17.2 {@link CartService#addProductToCartCommand(ProductProjection, Cart)}
 */
public class ExerciseMoodle17 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle17.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);
            final CartService cartService = new CartService(client);
            final ProductQueryService productQueryService = new ProductQueryService(client);
            final OrderService orderService = new OrderService(client);


            final ProductProjection productProjection
                    = client.execute(ProductProjectionByKeyGet.ofCurrent("RedWine")).toCompletableFuture().get();


            // Create and update an order
            // Use a customer you can get by key
            // Change the order state


            Order order = client.execute(CustomerByIdGet.of("feadde32-6300-4bbd-a0b2-11b97d2457ba"))
                    .thenComposeAsync(cartService::createCart)
                    .thenComposeAsync(cart -> cartService.addProductToCart(productProjection, cart))
                    .thenComposeAsync(orderService::createOrder)
                    .thenComposeAsync(orderNew -> orderService.changeState(orderNew, OrderState.CONFIRMED))
                    .toCompletableFuture()
                    .get();


            LOG.info("Created order {}", order);

        }
    }
}
