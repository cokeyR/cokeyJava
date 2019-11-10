package cokey.net.ssl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

//SSLSocket规定，不允许有出现半双工的情形，
//因此无法由发送发关闭到接收方的连接来表示发送完毕
//此处使用加长度头的方式，使用开始的一个字节，表示本次传输的数据的长度。且只接受和发送一次，然后就关闭连接。
public class SSLServer {

	// 监听端口
	private static final int PORT = 55555;
	// 服务器端私钥
	private static final String SERVER_KEY_STORE = "C:/Users/cokey/server.pfx";
	// 服务器端私钥的加密密钥（此处没有加密，为空）
	private static final String SERVER_KEY_STORE_PASSWORD = "";
	// 服务器端Socket
	private SSLServerSocket serverSocket = null;

	// 初始化网络端口
	public void init() throws Exception {

		// 1. 取SSLContext实例（此实例是Java对SSL支持的核心类）
		SSLContext context = SSLContext.getInstance("SSL");
		// 2. 加载服务器端私钥
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		keyStore.load(new FileInputStream(SERVER_KEY_STORE), SERVER_KEY_STORE_PASSWORD.toCharArray());
		
		// 3.初始化密钥管理器
		KeyManagerFactory factory = KeyManagerFactory.getInstance("SunX509");
		factory.init(keyStore, SERVER_KEY_STORE_PASSWORD.toCharArray());
		// 4.初始化sslcontext.
		context.init(factory.getKeyManagers(), null, null);
		// 5.取经过SSL协议增强的SSLServerSocket(扩展了ServerSocket)
		serverSocket = (SSLServerSocket) context.getServerSocketFactory().createServerSocket(PORT);
		System.out.println("SSLServerSocket初始化完成");
//		serverSocket.setNeedClientAuth(true);
	}
                    
	// 开始监听端口
	public void listen() throws IOException {
		Socket clientSocket = serverSocket.accept();

		InputStream inputStream = clientSocket.getInputStream();
		OutputStream outputStream = clientSocket.getOutputStream();
		// 1.读请求字节，并以GBK编码打印
		// 1.1读第一个表示数据总长度的字节（含头字节）
		int len = inputStream.read();
		byte[] bytes = new byte[len - 1];
		int read = inputStream.read(bytes);
		if (read != len - 1) {
			inputStream.close();
			outputStream.close();
			clientSocket.close();
			throw new IOException("数据头指明有" + len + "个字节，实际只收到了" + (read + 1) + "个字节");
		}
		// 2.打印请求信息
		String reqString = new String(bytes, "GBK");
		System.out.println("收到客户端" + clientSocket.getReuseAddress() + "的请求数据：" + reqString);
		// 3.根据AI核心代码-----写响应数据并关闭端口
		String res = reqString.replace("吗", "");
		res = res.replace("?", "!");
		res = res.replace("？", "！");
		byte[] resData = res.getBytes("GBK");
		System.out.println("开始响应客户端：" + res);
		// 3.1写数据头
		outputStream.write(resData.length + 1);
		// 3.2写数据体
		outputStream.write(resData);
		outputStream.flush();
		System.out.println("响应完毕，关闭客户端的连接");

		clientSocket.close();
	}

	public static void main(String[] args) throws Exception {
		SSLServer sslServer = new SSLServer();
		sslServer.init();
		System.out.println("开始使用SSL安全协议监听端口：" + PORT);
		int serverTimes = 10;
		while ((--serverTimes) > 0) {
			System.out.println("第" + (10 - serverTimes) + "监听，" + "一共能监听" + 10 + "次");
			try {
				sslServer.listen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
