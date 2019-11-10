package cokey.tomcat.core;

import java.io.IOException;
import java.net.Socket;

public interface Processor {

    void process(Socket socket) throws IOException;
}
