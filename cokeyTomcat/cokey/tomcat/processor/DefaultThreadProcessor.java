package cokey.tomcat.processor;

import cokey.tomcat.core.Processor;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DefaultThreadProcessor implements Processor {
    private Executor executor=  Executors.newFixedThreadPool(10);
    @Override
    public void process(Socket socket) throws IOException {
        try{
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = inputStream.readAllBytes();

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {

        }
    }
}
