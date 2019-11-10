package cokey.net.ssl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    private static String SERVER_IP="127.0.0.1";
    private static int SERVER_PORT=55555;


    public void sendReq(String reqMessage) throws IOException {
        Socket socket=new Socket(SERVER_IP,SERVER_PORT);
        //发送请求信息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(reqMessage.getBytes("GBK"));
        outputStream.flush();
        socket.shutdownOutput();
        //接受响应信息
        InputStream inputStream = socket.getInputStream();
        byte[] bytes=new byte[1024];
        int len=-1;
        ByteArrayOutputStream buffer=new ByteArrayOutputStream();
        while((len=inputStream.read(bytes))>1)
        	buffer.write(bytes,0,len);
        byte[] byteArray = buffer.toByteArray();
        String res=new String(byteArray,"GBK");
        //打印响应信息
        System.out.println(res);
        System.out.println(socket.getClass().getName());
        socket.shutdownInput();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        TCPClient client=new TCPClient();
        client.sendReq("hello");
        
    }
}
