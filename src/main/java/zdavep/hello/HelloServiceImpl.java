package zdavep.hello;

import com.twitter.finagle.tracing.Trace;
import com.twitter.util.Function0;
import com.twitter.util.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.None$;
import scala.runtime.BoxedUnit;

/**
 * Hello service implementation.
 */
final class HelloServiceImpl implements HelloService.FutureIface {

    // Logging
    private static final Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    /**
     * Echo a decorated message back to the client.
     */
    public Future<HelloMsg> sayHello(final HelloMsg msg) {
        log.info("sayHello(" + msg.name() + ")");
        return Trace.traceService("helloJava", "sayHello", None$.empty(), new Function0<Future<HelloMsg>>() {
            @Override public Future<HelloMsg> apply() {
                try { Thread.sleep(100L); } catch (InterruptedException e) {}
                return Future.value(new HelloMsg.Immutable("Hello, " + msg.name() + "!"));
            }
        });
    }

    /**
     * Log a message to stdout when called by the client.
     */
    public Future<BoxedUnit> ping() {
        log.info("ping()");
        return Trace.traceService("helloJava", "ping", None$.empty(), new Function0<Future<BoxedUnit>>() {
            @Override public Future<BoxedUnit> apply() {
                try { Thread.sleep(10L); } catch (InterruptedException e) {}
                return Future.Unit();
            }
        });
    }
}
