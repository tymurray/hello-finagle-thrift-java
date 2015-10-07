package zdavep.hello;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.tracing.Trace;
import com.twitter.util.Await;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello service server.
 */
public final class HelloServiceServer {

    // Logging
    private static final Logger log = LoggerFactory.getLogger(HelloServiceServer.class);

    /**
     * Start the hello service server.
     */
    public static void main(final String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        log.info("HelloService listening on: " + bindAddr);
        final ListeningServer server = Thrift.serveIface(bindAddr, new HelloServiceImpl());
        Trace.enable();
        Await.ready(server);
    }
}
