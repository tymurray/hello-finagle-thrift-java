package zdavep.hello;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Thrift;
import com.twitter.util.Await;

public final class HelloServiceServer {

    public static void main(String[] args) throws Throwable {
        final String bindAddr = "localhost:5555";
        System.out.println("HelloService listening on: " + bindAddr);
        final ListeningServer server = Thrift.serveIface(bindAddr, new HelloServiceImpl());
        Await.ready(server);
    }
}
