package zdavep.hello;

import com.twitter.util.Future;
import scala.runtime.BoxedUnit;

public final class HelloServiceImpl implements HelloService.FutureIface {

    public Future<HelloMsg> sayHello(final HelloMsg msg) {
        System.out.println("sayHello(" + msg.name() + ")");
        return Future.value(new HelloMsg.Immutable("Hello, " + msg.name() + "!"));
    }

    public Future<BoxedUnit> ping() {
        System.out.println("ping()");
        return Future.Unit();
    }
}
