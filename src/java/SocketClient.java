import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author liaohong
 * @date 2018/7/9 11:28
 */
public class SocketClient {
    public void client() {
        try {
            //创建Socket对象
            Socket socket = new Socket("localhost", 10000);
            System.out.println("客户端已启动,请求连接服务器");

            BufferedReader client = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter write = new PrintWriter(socket.getOutputStream());
            BufferedReader server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while (true) {
                System.out.print("客户端:");
                line = client.readLine();
                write.write(line + "\n");
                write.flush();
                System.out.println("服务器" + ":" + server.readLine());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SocketClient().client();
    }
}
