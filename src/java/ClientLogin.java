/**
 * @author liaohong
 * @date 2018/7/9 16:31
 * 在本类109行附近调用了ChatClient类
 * 在本类109行附近调用了ChatClient类
 */
/**在本类109行附近调用了ChatClient类
 *
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class ClientLogin extends JFrame {
    private JTextField nametext;
    private JPasswordField passwordtetx;
    //private Object bPanel;

    public ClientLogin() {
        this.init();       //init方法初始化
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void init() {
        this.setTitle("聊天室");
        this.setSize(330, 230);     //借用成熟美观尺寸
        int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        /**以上的方法要是不借助Eclipse实在是很难记住，尼玛！
         *
         */
        this.setLocation((x - this.getWidth()) / 2, (y - this.getHeight()) / 2);
        this.setResizable(false);     //不允许用户自行更改大小

        JPanel mainPanel = new JPanel();
        this.add(mainPanel, BorderLayout.CENTER);     //将主面板加入frame
        mainPanel.setLayout(null);
        JLabel namelabel = new JLabel("请输入昵称");
        namelabel.setBounds(30, 30, 80, 22);
        mainPanel.add(namelabel);
        nametext = new JTextField();
        nametext.setBounds(115, 30, 120, 22);
        mainPanel.add(nametext);

        //接下来按钮位置排放
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(bPanel, BorderLayout.SOUTH);
        JButton reset = new JButton("重置");
        reset.addActionListener(new ActionListener() {    //为“重置”按钮添加事件监听
            public void actionPerformed(ActionEvent e) {
                nametext.setText("");
                passwordtetx.setText("");
            }
        });
        bPanel.add(reset);

        /**下面开始实现提交按钮
         *
         */

        JButton submit = new JButton("登陆");
        submit.addActionListener(new LoginAction(this));  //因为登陆相对复杂，重新为登陆写一个类
        bPanel.add(submit);
    }


    /**下面开始写登陆类
     *
     */

    class LoginAction implements ActionListener {
        private JFrame self;

        public LoginAction(JFrame self) {
            this.self = self;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                //开始连接到服务器
                Socket socket = new Socket("127.0.0.1", 8888);
                new ChatClient(socket, nametext.getText());
                self.dispose();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                JOptionPane.showConfirmDialog(self, "找不到指定服务器!~", "连接失败", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
                JOptionPane.showConfirmDialog(self, "连接服务器出错，请重试！", "连接失败", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        new ClientLogin();
    }
}
