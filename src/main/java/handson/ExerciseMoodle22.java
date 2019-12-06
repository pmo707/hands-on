package handson;

import io.sphere.sdk.client.SphereClient;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.Reference;
import io.sphere.sdk.states.State;
import io.sphere.sdk.states.StateDraft;
import io.sphere.sdk.states.StateDraftBuilder;
import io.sphere.sdk.states.StateType;
import io.sphere.sdk.states.commands.StateCreateCommand;
import io.sphere.sdk.states.commands.StateUpdateCommand;
import io.sphere.sdk.states.commands.updateactions.SetTransitions;
import io.sphere.sdk.states.queries.StateByIdGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static handson.impl.ClientService.createSphereClient;


public class ExerciseMoodle22 {
    private static final Logger LOG = LoggerFactory.getLogger(ExerciseMoodle22.class);


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        try (final SphereClient client = createSphereClient()) {

            /*
                Be careful execute stepwise.
                You need the correct id's. This is for demo purposes only. Better to be scripted.
             */


            // Part I: Store States
            // Execute this part first ONLY.

//            StateDraft stateOrderPacked = StateDraftBuilder.of("OrderPacked", StateType.ORDER_STATE).initial(true)
//                    .name(LocalizedString.of(Locale.US, "Order Packed"))
//                    .build();
//
//
//            StateDraft stateOrderShipping = StateDraftBuilder.of("OrderShipping", StateType.ORDER_STATE).initial(true)
//                    .name(LocalizedString.of(Locale.US, "Order Shipping"))
//                    .build();
//
//            State statePacked = client.execute(StateCreateCommand.of(stateOrderPacked)).toCompletableFuture().get();
//            State stateShipping = client.execute(StateCreateCommand.of(stateOrderShipping)).toCompletableFuture().get();
//
//            LOG.info("statePacked info {}", statePacked);
//            LOG.info("stateShipping info {}", stateShipping);

            // Part II: Store Transitions
            // Put correct id's. Then execute this part ONLY.
            final Reference<State> endState = State.referenceOfId("001d50e6-2b40-4ded-8688-391fdcd342a4");
            Set<Reference<State>> set = new HashSet<>();
            set.add(endState);
            final SetTransitions setTransitions = SetTransitions.of(set);

            final StateByIdGet stateByIdGet = StateByIdGet.of("a9893c22-708e-48b9-ae7d-16e10d4f0bd7");

            State startState = client.execute(stateByIdGet).toCompletableFuture().get();

            final StateUpdateCommand stateUpdateCommand = StateUpdateCommand.of(startState, setTransitions);

            State state = client.execute(stateUpdateCommand).toCompletableFuture().get();


             LOG.info("State info {}", state);

        }

    }
}
