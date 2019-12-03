package handson.impl;

import io.sphere.sdk.carts.*;
import io.sphere.sdk.carts.commands.CartCreateCommand;
import io.sphere.sdk.carts.commands.CartUpdateCommand;
import io.sphere.sdk.carts.commands.updateactions.AddDiscountCode;
import io.sphere.sdk.carts.commands.updateactions.AddLineItem;
import io.sphere.sdk.categories.queries.CategoryQuery;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.Address;
import io.sphere.sdk.models.DefaultCurrencyUnits;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.commands.ProductUpdateCommand;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * This class provides operations to work with {@link Cart}s.
 */
public class CartService extends AbstractService {

    public CartService(SphereClient client) {
        super(client);
    }

    /**
     * Creates a cart for the given customer.
     *
     * @param customer the customer
     * @return the customer creation completion stage
     */
    public CompletionStage<Cart> createCart(final Customer customer) {
        // TODO Task15.1. Create a cart
        // Make sure the customer has a default shipping address!!
        //
        String email = customer.getEmail();
        CartDraft cartDraft = CartDraft.of(DefaultCurrencyUnits.EUR)
                .withCustomerEmail(email)
                .withShippingAddress(customer.getDefaultShippingAddress())
                .withCustomerId(customer.getId());

        CartCreateCommand cartCreateCommand = CartCreateCommand.of(cartDraft);

        return client.execute(cartCreateCommand);
    }

    /**
     * Adds the given product to the given cart.
     *
     * @param product the product
     * @param cart    the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addProductToCart(final ProductProjection product, final Cart cart) {
        // TODO Task15.2. Add line item to a cart

        CartUpdateCommand cartUpdateCommand = addProductToCartCommand(product, cart);

        return client.execute(cartUpdateCommand);
    }


    public CartUpdateCommand addProductToCartCommand(final ProductProjection product, final Cart cart) {
        // TODO 17.2. Add line item to a cart
        // Return Command!!

        return CartUpdateCommand
                .of(cart, AddLineItem.of(product.getId(), product.getMasterVariant().getId(), 1));
    }


    /**
     * Adds the given discount code to the given cart.
     *
     * @param code the discount code
     * @param cart the cart
     * @return the cart update completion stage
     */
    public CompletionStage<Cart> addDiscountToCart(final String code, final Cart cart) {
        // TODO 18.1 Add discount code to cart

        return null;
    }
}
