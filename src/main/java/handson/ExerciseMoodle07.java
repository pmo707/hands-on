package handson;

import handson.impl.CustomerService;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerSignInResult;
import io.sphere.sdk.customers.CustomerToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;

/**
 * Registers a new customer.
 * <p>
 * See:
 * TODO Task07.1 {@link CustomerService#createCustomer(String, String)}
 * TODO Task07.2 {@link CustomerService#createEmailVerificationToken(Customer, Integer)}
 * TODO Task07.3 {@link CustomerService#verifyEmail(CustomerToken)}
 */
public class ExerciseMoodle07 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle07.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);


            final String email = String.format("%s@oskar.com", UUID.randomUUID().toString());
            final String password = "password";
            final CustomerSignInResult customerCreationResult = customerService.createCustomer(email, password)
                    .toCompletableFuture().get();
            System.out.println(email);

            LOG.info("Registered customer {}", customerCreationResult);
        }
    }
}
