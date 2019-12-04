package handson;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.TextInputHint;
import io.sphere.sdk.types.*;
import io.sphere.sdk.types.commands.TypeCreateCommand;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle20 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle20.class);


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            // Create a shoe size custom type on customers

            TypeDraft typeDraft = TypeDraftBuilder.of("shor",
                    LocalizedString.of(Locale.US, "shoe size"),
                    ResourceTypeIdsSetBuilder.of().addCustomers())
                    .fieldDefinitions(Arrays.asList(FieldDefinition.of(NumberFieldType.of(), "shoe-size",
                            LocalizedString.of(Locale.US, "shoe size"), true, TextInputHint.SINGLE_LINE)))
                    .build();
            TypeCreateCommand typeCreateCommand = TypeCreateCommand.of(typeDraft);
            client.execute(typeCreateCommand).toCompletableFuture().get();


        }
    }
}
