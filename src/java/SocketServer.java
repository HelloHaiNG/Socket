import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liaohong
 * @date 2018/7/9 11:23
 */
public class SocketServer {

    public void server() {
        try {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(10000);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务端已启动，等待客户端连接..");
            Socket socket = serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象
            System.out.println("建立连接");

            String line;
            BufferedReader client = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader server = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("客户端" + ":" + client.readLine());
                System.out.print("服务器:");
                line = server.readLine();
                writer.write(line + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.server();
    }
}
