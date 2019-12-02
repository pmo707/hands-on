package handson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.customobjects.CustomObject;
import io.sphere.sdk.customobjects.CustomObjectDraft;
import io.sphere.sdk.customobjects.commands.CustomObjectUpsertCommand;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.TextInputHint;
import io.sphere.sdk.types.*;
import io.sphere.sdk.types.commands.TypeCreateCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle21 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle21.class);


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            // TODO: Create some fancy data to store
            //
            JsonNode jsonNode = null;

            // Create a custom object draft
            // Upsert: Update/Insert-command
            final CustomObject<JsonNode> jsonNodeCustomObject = null;


            LOG.info("Custom Object info {}", jsonNodeCustomObject);



        }
    }
}
