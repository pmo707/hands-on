package handson;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.extensions.Extension;
import io.sphere.sdk.extensions.ExtensionDraft;
import io.sphere.sdk.extensions.ExtensionDraftBuilder;
import io.sphere.sdk.extensions.ExtensionResourceType;
import io.sphere.sdk.extensions.HttpDestination;
import io.sphere.sdk.extensions.HttpDestinationAuthentication;
import io.sphere.sdk.extensions.Trigger;
import io.sphere.sdk.extensions.TriggerType;
import io.sphere.sdk.extensions.commands.ExtensionCreateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle24 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle24.class);


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {


            // Create an API extension on Creation of Carts
            HttpDestination httpDestination = new HttpDestination() {
                @Override
                public String getUrl() {
                    return "https://www.google.com.ua";
                }

                @Override
                public HttpDestinationAuthentication getAuthentication() {
                    return null;
                }
            };

            Trigger trigger = new Trigger() {
                @Override
                public ExtensionResourceType getResourceTypeId() {
                    return ExtensionResourceType.CART;
                }

                @Override
                public List<TriggerType> getActions() {
                    return null;
                }
            };
            List<Trigger> triggers = new ArrayList<>();
            triggers.add(trigger);

            ExtensionDraft extensionDraft = ExtensionDraftBuilder.of("CartCreateExtension", httpDestination, triggers).build();
            final Extension extension = client.execute(ExtensionCreateCommand.of(extensionDraft)).toCompletableFuture().get();

            LOG.info("Created extension {}", extension);
        }
    }
}
