package cokey.net.ssl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class SSLClient {

	private static final String SERVER_IP = "cokey.xyz";
	private static final int SERVER_PORT = 55555;
	private static final String SERVER_CTR_Path = "C:/Users/cokey/server.pfx";
	private static final String SERVER_KEY_STORE_PASSWORD = "";
	private SSLSocket socket = null;

	// 初始化连接端口
	public void init() throws Exception {

		// 1. 取SSLContext实例（此实例是Java对SSL支持的核心类）
		SSLContext context = SSLContext.getInstance("SSL");
		// 2. 加载服务器端证书（证书也是密钥）
		KeyStore trustKeyStore = KeyStore.getInstance("PKCS12");
		trustKeyStore.load(new FileInputStream(SERVER_CTR_Path), SERVER_KEY_STORE_PASSWORD.toCharArray());
		// 3.初始化密钥管理器
		TrustManagerFactory factory = TrustManagerFactory.getInstance("SunX509");
		factory.init(trustKeyStore);
		// 4.初始化sslcontext.
		context.init(null, factory.getTrustManagers(), null);
		// 5.取经过SSL协议增强的SSLServerSocket(扩展了ServerSocket)
		socket=(SSLSocket) context.getSocketFactory().createSocket(SERVER_IP,SERVER_PORT);
		if(socket instanceof SSLSocket)
			System.out.println("SSLSocket初始化完成");
	}
	
	public void sendReqMessage(String msg) throws Exception {
		//1.取要发送的字符串的GBK编码的字节数组
		byte[] reqData = msg.getBytes("GBK");
		//2.发送请求字节流
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(reqData.length+1);
		outputStream.write(reqData);
		outputStream.flush();
		//2.接受响应，并以GBK编码打印
		InputStream inputStream = socket.getInputStream();
		int len=inputStream.read();
		byte[] bytes=new byte[len-1];
		int read = inputStream.read(bytes);
		if(read!=len-1) {
			inputStream.close();
			outputStream.close();
			socket.close();
			throw new IOException("数据头指明有"+len+"个字节，实际只收到了"+(read+1)+"个字节");
		}
		String resStr=new String(bytes,"GBK");
		System.out.println("收到服务器通过SSL的响应："+resStr);
		System.out.println("关闭端口");
		socket.close();
	}
	
	public static void main(String[] args) throws Exception {
		SSLClient client=new SSLClient();
		client.init();
		String reqMsg="会说英语吗？";
		System.out.println(reqMsg);
		client.sendReqMessage(reqMsg);
	}
}
