package handson.impl;

import io.sphere.sdk.client.SphereAccessTokenSupplier;
import io.sphere.sdk.client.SphereAsyncHttpClientFactory;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.client.SphereClientConfig;
import io.sphere.sdk.http.HttpClient;

import java.io.IOException;
import java.util.Properties;

public class ClientService {
    /**
     * Creates a blocking sphere client
     *
     * @return Sphere client
     * @throws IOException
     */
    public static SphereClient createSphereClient() throws IOException {
        final SphereClientConfig clientConfig = loadCTPClientConfig();

        //TODO Task06.3 Create the client
        final HttpClient httpClient = new SphereAsyncHttpClientFactory().getClient();
        final SphereAccessTokenSupplier sphereAccessTokenSupplier =
                SphereAccessTokenSupplier.ofAutoRefresh(clientConfig, httpClient, true);
        return SphereClient.of(clientConfig, httpClient, sphereAccessTokenSupplier);
    }

    /**
     * Sets a sphere client configuration
     *
     * @return sphere client configuration
     * @throws IOException
     */
    private static SphereClientConfig loadCTPClientConfig() throws IOException {
        //TODO Task06.2 Create the configuration for the sphere client
        final Properties prop = new Properties();
        prop.load(ClientService.class.getResourceAsStream("/dev.properties"));
        SphereClientConfig clientConfig = SphereClientConfig.ofProperties(prop, "");

        return clientConfig;


    }
}