package osgi.enroute.examples.eval.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import osgi.enroute.debug.api.Debug;
import osgi.enroute.examples.eval.api.Eval;

import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * Created by kgolebiowski on 01/06/2017.
 */
@Component(property = {
        Debug.COMMAND_SCOPE + "=eval",
        Debug.COMMAND_FUNCTION + "=eval",
}, service = EvalCommand.class)
public class EvalCommand {

    @Reference
    Eval greeter;

    public double eval(String... name) throws Exception {
        return greeter.eval(Stream.of(name).collect(joining(" ")));
    }
}
