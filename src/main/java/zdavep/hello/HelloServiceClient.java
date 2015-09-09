package zdavep.hello;

import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Function;
import scala.runtime.BoxedUnit;

public final class HelloServiceClient {

    protected static Function<HelloMsg, BoxedUnit> printReply = new Function<HelloMsg, BoxedUnit>() {
        public BoxedUnit apply(HelloMsg msg) {
            System.out.println("Received response: " + msg.name());
            return null;
        }
    };

    public static void main(String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        System.out.println("Calling HelloService on: " + bindAddr);
        HelloService.FutureIface client = Thrift.newIface(bindAddr, HelloService.FutureIface.class);
        HelloMsg req = new HelloMsg.Immutable("from Java");
        Await.ready(client.sayHello(req).map(printReply));
    }
}
