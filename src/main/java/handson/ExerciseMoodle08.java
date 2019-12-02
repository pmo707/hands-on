package handson;

import handson.impl.CustomerService;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerSignInResult;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.customers.queries.CustomerByIdGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;

/**
 * Registers a new customer.
 * <p>
 * See:
 * TODO 2.1 {@link CustomerService#createCustomer(String, String)}
 * TODO 2.2 {@link CustomerService#createEmailVerificationToken(Customer, Integer)}
 * TODO 2.3 {@link CustomerService#verifyEmail(CustomerToken)}
 */
public class ExerciseMoodle08 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle08.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {
            final CustomerService customerService = new CustomerService(client);


            // Part 1: TODO: Register and verify a second customer
            //
            final String email = "YOUR-NAME-2@example.com";
            final String password = "password";

            final Customer customer = customerService.createCustomer(email, password).toCompletableFuture()
                    .get().getCustomer();



            LOG.info("Registered customer {}", customer);

            // Part 2: TODO: Verify now also the first customer from MoodleExercise07 (previous task)
            // Problem: We have no key to get the customer.
            // Solution: Get the id from URL in MC or API pülayground
            //

            Customer customerVerified = null;
            LOG.info("Registered customer {}", customerVerified);


        }
    }
}
