/**
 * @author liaohong
 * @date 2018/7/9 16:33
 */

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer {
    private List<Socket> sockets = new ArrayList<Socket>();    //类集的应用

    public ChatServer() throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("服务器已在监听8888端口");

        while (true) {
            Socket socket = ss.accept();
            sockets.add(socket);
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println("新用户进入！ip是" + ip);
            Thread thread = new Thread(new ServerRunner(sockets, socket));
            thread.start();
        }
    }

    public static void main(String args[]) {
        try {
            new ChatServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ServerRunner implements Runnable {
    private List<Socket> sockets;
    private Socket currentSocket;   //当前socket

    public ServerRunner(List<Socket> sockets, Socket currentSocket) {
        this.sockets = sockets;
        this.currentSocket = currentSocket;
    }

    public void run() {
        String ip = currentSocket.getInetAddress().getHostAddress();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
            String str = null;
            while ((str = br.readLine()) != null) {
                System.out.println(ip + "说" + str);
                //往所有的客户端写入信息

                for (Socket temp : sockets) {
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(temp.getOutputStream()));
                    pw.println(str);
                    pw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

