package osgi.enroute.examples.eval.application;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.*;
import osgi.enroute.examples.eval.api.Eval;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by kgolebiowski on 01/06/2017.
 */

@RequireAngularWebResource(resource = {"angular.js", "angular-resource.js", "angular-route.js"}, priority = 1000)
@RequireBootstrapWebResource(resource = "css/bootstrap.css")
@RequireWebServerExtender
@Component(name = "osgi.enroute.examples.eval")
public class EvalApplication implements REST {

    @Reference
    Eval eval;

    @Reference
    ConfigurationAdmin ca;

    public double getEval(String expression) throws Exception {
        return eval.eval(expression);
    }

    /* One of below methods breaks the application causing it to throw the following
        exception when user clicks on Eval button:

        java.io.FileNotFoundException: No such method geteval/1. Available: {}
                at osgi.enroute.rest.simple.provider.RestMapper.execute(RestMapper.java:259)
                at osgi.enroute.rest.simple.provider.RestServlet.service(RestServlet.java:66)
                at javax.servlet.http.HttpServlet.service(HttpServlet.java:725)
                at org.apache.felix.http.base.internal.handler.ServletHandler.handle(ServletHandler.java:85)
                at org.apache.felix.http.base.internal.dispatch.InvocationChain.doFilter(InvocationChain.java:79)
                at org.apache.felix.http.base.internal.dispatch.Dispatcher.dispatch(Dispatcher.java:124)
                at org.apache.felix.http.base.internal.DispatcherServlet.service(DispatcherServlet.java:61)
                at javax.servlet.http.HttpServlet.service(HttpServlet.java:725)
                at org.eclipse.jetty.servlet.ServletHolder.handle(ServletHolder.java:845)
                at org.eclipse.jetty.servlet.ServletHandler.doHandle(ServletHandler.java:583)
                at org.eclipse.jetty.server.session.SessionHandler.doHandle(SessionHandler.java:224)
                at org.eclipse.jetty.server.handler.ContextHandler.doHandle(ContextHandler.java:1160)
                at org.eclipse.jetty.servlet.ServletHandler.doScope(ServletHandler.java:511)
                at org.eclipse.jetty.server.session.SessionHandler.doScope(SessionHandler.java:185)
                at org.eclipse.jetty.server.handler.ContextHandler.doScope(ContextHandler.java:1092)
                at org.eclipse.jetty.server.handler.ScopedHandler.handle(ScopedHandler.java:141)
                at org.eclipse.jetty.server.handler.ContextHandlerCollection.handle(ContextHandlerCollection.java:213)
                at org.eclipse.jetty.server.handler.HandlerWrapper.handle(HandlerWrapper.java:134)
                at org.eclipse.jetty.server.Server.handle(Server.java:518)
                at org.eclipse.jetty.server.HttpChannel.handle(HttpChannel.java:308)
                at org.eclipse.jetty.server.HttpConnection.onFillable(HttpConnection.java:244)
                at org.eclipse.jetty.io.AbstractConnection$ReadCallback.succeeded(AbstractConnection.java:273)
                at org.eclipse.jetty.io.FillInterest.fillable(FillInterest.java:95)
                at org.eclipse.jetty.io.SelectChannelEndPoint$2.run(SelectChannelEndPoint.java:93)
                at org.eclipse.jetty.util.thread.strategy.ExecuteProduceConsume.produceAndRun(ExecuteProduceConsume.java:246)
                at org.eclipse.jetty.util.thread.strategy.ExecuteProduceConsume.run(ExecuteProduceConsume.java:156)
                at org.eclipse.jetty.util.thread.QueuedThreadPool.runJob(QueuedThreadPool.java:654)
                at org.eclipse.jetty.util.thread.QueuedThreadPool$3.run(QueuedThreadPool.java:572)
                at java.lang.Thread.run(Thread.java:748)
    */

    /* public EvalApplication() {
        System.out.println("Constructor of EvalApplication");
    }

    @Activate
    void activate(ComponentContext cc, BundleContext bc, Map<String, Object> config) {
        System.out.println("activate");
        System.out.println("Component properties: ");
        config.forEach((s, o) -> System.out.println(s + ":" + o));

        try {
            Arrays.stream(ca.listConfigurations(null))
                    .forEach(configuration -> System.out.println("Configuration: " + configuration));
        } catch (IOException | InvalidSyntaxException e) {
            e.printStackTrace();
        }
    }

    @Deactivate
    void deactivate(ComponentContext cc, BundleContext bc, Map<String, Object> config) {
        System.out.println("deactivate");

    }

    @Modified
    void modified(Map<String, Object> config) {
        System.out.println("modified");
    } */
}