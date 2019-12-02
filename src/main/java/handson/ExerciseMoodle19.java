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
import java.util.Base64;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;



// TODO: Use a different HTTP client, like retrofit
//
public class ExerciseMoodle19 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle19.class);


    public void getToken() {

        // CustomerEmail & Password
        // Encode Base64 by Hand !!
        String customerEmail = "YOUR CUSTOMER EMAIL";
        String customerLogon = "YOUR CUSTOMER PASSWORD";

        // ClientId for a MyAPI-Client
        String clientID = "CLIENT ID";
        // ClientSecret for a MyAPI-Client
        String clientSecret = "CLIENT SECRET";

        OkHttpClient myAPIClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String projectID = "PROJECT ID";
        Response response = null;

        try {
            String encoding = Base64.getEncoder().encodeToString(new String(clientID + ":" + clientSecret).getBytes("UTF-8"));
            RequestBody body = RequestBody.create(mediaType, "grant_type=password&username=" + customerEmail + "&password=" + customerLogon);

            // Request for token
            Request oAuthRequest = new Request.Builder()
                    .url("https://auth.sphere.io/oauth/" + projectID + "/customers/token")
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

        String meToken = "TOKEN";

        OkHttpClient myAPIClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String projectID = "PROJECT ID";
        Response response = null;

        try {

            Request oAuthRequest = new Request.Builder()
                    .url("https://api.sphere.io/" + projectID + "/me/orders")
                    .get()
                    .addHeader("Authorization", "Bearer " + meToken)
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


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            ExerciseMoodle19 task = new ExerciseMoodle19();
            // task.getToken();
            // task.getMeOrders();

        }
    }
}
