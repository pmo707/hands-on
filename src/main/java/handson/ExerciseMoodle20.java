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
import java.util.ArrayList;
import java.util.Base64;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;



public class ExerciseMoodle20 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle20.class);




    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            // Create a shoe size custom type on customers

            final Type shoeSizeType = null;

            LOG.info("Custom Type info {}", shoeSizeType);


        }
    }
}
