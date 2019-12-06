package handson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import handson.impl.CustomerService;
import io.sphere.sdk.client.JsonNodeSphereRequest;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.CustomerToken;
import io.sphere.sdk.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;



public class ExerciseMoodle25 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle25.class);

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {



            String jsonString_SimpleRequest = "{\"query\": \"query ($sku: String!) {product(sku: $sku) {id version}}\", \"variables\": {\"sku\": \"pihnastyi-SKU101\"} }";

            final JsonNode jsonNodeBody = new ObjectMapper().readTree(jsonString_SimpleRequest);
            final JsonNode jsonNodeAnswer = client.execute(JsonNodeSphereRequest.of(HttpMethod.POST,
                    "/graphql",
                    jsonNodeBody)).toCompletableFuture().get();

            LOG.info("JSON answer is {} ", jsonNodeAnswer);

        }
    }
}
