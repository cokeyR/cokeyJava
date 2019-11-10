package cokey.tomcat.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * cokeyTomcat的监听类，从GLobalContext中读配置信息，然后初始并启动端口监听
 */
public class Listener {

    private ServerSocket serverSocket;

    private void init() throws IOException {
        serverSocket=new ServerSocket(GlobalContext.DEPLOY_PORT);
    }

    private void listen(Processor processor) throws IOException {
        Socket clientSocket = serverSocket.accept();
        processor.process(clientSocket);
    }

    public void startListen(){

    }
}
