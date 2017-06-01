package osgi.enroute.examples.eval.application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import osgi.enroute.examples.eval.api.Eval;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

/**
 * Created by kgolebiowski on 01/06/2017.
 */

@RequireAngularWebResource(resource={"angular.js","angular-resource.js", "angular-route.js"}, priority=1000)
@RequireBootstrapWebResource(resource="css/bootstrap.css")
@RequireWebServerExtender
@Component(name = "osgi.enroute.examples.eval")
public class EvalApplication implements REST {

    @Reference
    Eval eval;

    public double getEval(String expression) throws Exception {
        return eval.eval(expression);
    }
}
