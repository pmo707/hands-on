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
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


// TODO: Use a different HTTP client, like retrofit
//
public class ExerciseMoodle19 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle19.class);
    private static final String API_URL = "https://api.commercetools.co/";

    public void getToken() {

        // CustomerEmail & Password
        // Encode Base64 by Hand !!
        String customerEmail = "b270689f-25b4-4289-838b-fe9785d34eda@oskar.com";
        String customerLogon = "password";

        // ClientId for a MyAPI-Client
        String clientID = "EY6dDNohD0gQWf6xX7-mPOoP";
        // ClientSecret for a MyAPI-Client
        String clientSecret = "6rKD5vhEJEMVJQBMWqDefs95HwwXvHDU";

        OkHttpClient myAPIClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String projectID = "oskar";
        Response response = null;

        try {
            String encoding = Base64.getEncoder().encodeToString((clientID + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
            RequestBody body = RequestBody.create(mediaType, "grant_type=password&username=" + customerEmail + "&password=" + customerLogon);

            // Request for token
            Request oAuthRequest = new Request.Builder()
                    .url("https://auth.commercetools.co/oauth/" + projectID + "/customers/token")
                    .post(body)
                    .addHeader("Authorization", "Basic " + encoding)
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = myAPIClient.newCall(oAuthRequest).execute();

            final byte[] bytes = IOUtils.toByteArray(response.body().byteStream());
            String bodyString = new String(bytes, "UTF-8");
            LOG.info("Token Answer: " + bodyString);
        } catch (IOException e) {
            LOG.info("Execption" + e.toString());
        }
        response.body().close();


    }

    public void getMeOrders() {

        String meToken = "v8OUyys5yVFhgF8U1ohNoTRqFzxer2QV";

        OkHttpClient myAPIClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String projectID = "oskar";
        Response response = null;

        try {

            Request oAuthRequest = new Request.Builder()
                    .url(API_URL + projectID + "/me/orders")
                    .get()
                    .addHeader("Authorization", "Bearer " + meToken)
                    .addHeader("cache-control", "no-cache")
                    .build();

            response = myAPIClient.newCall(oAuthRequest).execute();

            final byte[] bytes = IOUtils.toByteArray(response.body().byteStream());
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            LOG.info("Token Answer: " + bodyString);
        } catch (IOException e) {
            LOG.info("Execption" + e.toString());
        }
        closeResponse(response);

    }

    private void closeResponse(Response response) {
        if (response != null && (response.body() != null)) {
            response.body().close();
        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            ExerciseMoodle19 task = new ExerciseMoodle19();
            task.getToken();
            task.getMeOrders();

        }
    }
}
