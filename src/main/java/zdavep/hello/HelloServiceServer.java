package zdavep.hello;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;

/**
 * Hello service server.
 */
public final class HelloServiceServer {

    /**
     * Start the hello service server.
     */
    public static void main(final String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        System.out.println("HelloService listening on: " + bindAddr);
        final ListeningServer server = Thrift.serveIface(bindAddr, new HelloServiceImpl());
        Await.ready(server);
    }
}
