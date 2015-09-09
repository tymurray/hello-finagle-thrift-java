package zdavep.hello;

import com.twitter.util.Future;
import scala.runtime.BoxedUnit;

/**
 * Hello service implementation.
 */
public final class HelloServiceImpl implements HelloService.FutureIface {

    /**
     * Echo a decorated message back to the client.
     */
    public Future<HelloMsg> sayHello(final HelloMsg msg) {
        System.out.println("sayHello(" + msg.name() + ")");
        return Future.value(new HelloMsg.Immutable("Hello, " + msg.name() + "!"));
    }

    /**
     * Log a message to stdout when called by the client.
     */
    public Future<BoxedUnit> ping() {
        System.out.println("ping()");
        return Future.Unit();
    }
}
