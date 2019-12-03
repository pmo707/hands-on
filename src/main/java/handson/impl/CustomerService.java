package handson.impl;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.commands.Command;
import io.sphere.sdk.customers.*;
import io.sphere.sdk.customers.commands.CustomerCreateCommand;
import io.sphere.sdk.customers.commands.CustomerCreateEmailTokenCommand;
import io.sphere.sdk.customers.commands.CustomerVerifyEmailCommand;

import java.util.concurrent.CompletionStage;

/**
 * This class provides operations to work with {@link Customer}s.
 */
public class CustomerService extends AbstractService {

    public CustomerService(final SphereClient client) {
        super(client);
    }

    /**
     * Creates a new customer {@link Customer} with the given parameters.
     *
     * @param email    the customers email
     * @param password the customers password
     * @return the customer creation completion stage
     */
    public CompletionStage<CustomerSignInResult> createCustomer(final String email, final String password) {

        final CustomerDraft draft = CustomerDraftDsl.of(email, password);

            final CustomerCreateCommand sphereRequest = CustomerCreateCommand.of(draft);

        return client.execute(sphereRequest);

    }

    /**
     * Creates an email verification token for the given customer.
     * This is then used to create a password reset link.
     *
     * @param customer            the customer
     * @param timeToLiveInMinutes the time to live (in minutes) for the token
     * @return the customer token creation completion stage
     */
    public CompletionStage<CustomerToken> createEmailVerificationToken(final Customer customer, final Integer timeToLiveInMinutes) {

        // TODO Task08.1 Create an email verification token
        final Command<CustomerToken> createTokenCommand = CustomerCreateEmailTokenCommand.of(customer, timeToLiveInMinutes);

        return client.execute(createTokenCommand);
    }

    /**
     * Verifies the customer token.
     *
     * @param customerToken the customer token
     * @return the email verification completion stage
     */
    public CompletionStage<Customer> verifyEmail(final CustomerToken customerToken) {

        // TODO Task08.2 Verify the customer token
        final Command<Customer> verifyEmailCommand = CustomerVerifyEmailCommand.ofCustomerToken(customerToken);
        CompletionStage<Customer> customer = client.execute(verifyEmailCommand);
        return customer;
    }
}
