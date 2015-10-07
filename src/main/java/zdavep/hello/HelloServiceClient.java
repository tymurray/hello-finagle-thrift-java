package zdavep.hello;

import com.twitter.finagle.Thrift;
import com.twitter.util.Await;
import com.twitter.util.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.runtime.BoxedUnit;

/**
 * Hello service client.
 */
public final class HelloServiceClient {

    // Logging
    private static final Logger log = LoggerFactory.getLogger(HelloServiceClient.class);

    /**
     * Print a reply from the service to stdout.
     */
    private static final Function<HelloMsg, BoxedUnit> printReply = new Function<HelloMsg, BoxedUnit>() {
        public BoxedUnit apply(final HelloMsg msg) {
            log.info("Received response: " + msg.name());
            return null;
        }
    };

    /**
     * Call the sayHello function on the remote hello service.
     */
    public static void main(final String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        log.info("Calling HelloService on: " + bindAddr);
        final HelloService.FutureIface client = Thrift.newIface(bindAddr, HelloService.FutureIface.class);
        final HelloMsg req = new HelloMsg.Immutable("from Java");
        Await.ready(client.sayHello(req).map(printReply));
    }
}
