import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class serverClinent {
    static Socket socket = null;

    static ServerSocket serverSock = null;
    static BufferedReader br = null;
    //static BufferedWriter bw = null;
    static int clientCounts = 0;
    public static void main(String[] args) {

        BufferedWriter out = null;
        BufferedReader in = null;
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        //组件
        JFrame jFrame = new JFrame("客户器端");
        jFrame.setBounds(300, 300, 630, 330);
        jFrame.getContentPane().setLayout(new BorderLayout());

        JPanel jpane1 = new JPanel();
        JPanel jpane2 = new JPanel(new FlowLayout(FlowLayout.LEADING,10,5));
        JTextArea textArea1 = new JTextArea(11,50);
        JScrollPane jScrollPane3 = new JScrollPane(textArea1);

        JLabel  jlabel1 = new JLabel("通信主机名:");
        JLabel  jlabel2 = new JLabel("服务器端口号");
        JLabel  jlabel3 = new JLabel("待发送消息");


        JTextField jTextField1 =new JTextField(10);
        JTextField jTextField2 =new JTextField(10);
        JTextField jTextField3 =new JTextField(20);

        JButton jButton1 = new JButton("启动服务器");
        JButton jbutton2 = new JButton("发送消息");

        //-----窗体添加-------
        jpane1.add(jlabel1);
        jpane1.add(jTextField1);
        jpane1.add(jlabel2 );
        jpane1.add(jTextField2);
        jpane1.add(jButton1);
        jpane2.add(jlabel3);
        jpane2.add(jTextField3);
        jpane2.add(jbutton2);

        /*---增加监听事件------*/

        jButton1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    socket = new Socket("localhost",9999);
                    System.out.println("连接成功");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BufferedWriter bw = null;
                try{
                    bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    bw.write(jTextField3.getText());
                    bw.newLine();
                    bw.flush();
                    System.out.println("asd");
                    textArea1.append(jTextField3.getText());
                }catch (IOException e1){
                    e1.printStackTrace();

                }
            }
        });
        //-----窗体结构-------
        javax.swing.border.Border titleBorder1=BorderFactory.createTitledBorder("服务器启动面板");
        javax.swing.border.Border titleBorder2=BorderFactory.createTitledBorder("服务器通信面板");
        javax.swing.border.Border titleBorder3=BorderFactory.createTitledBorder("服务器启动面板");
        jpane1.setBorder(titleBorder1);
        jpane2.setBorder(titleBorder2);
        jFrame.getContentPane().add(jpane1, BorderLayout.PAGE_START);
        jFrame.getContentPane().add(jpane2, BorderLayout.CENTER);
        jFrame.getContentPane().add(jScrollPane3, BorderLayout.PAGE_END);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    public void showUI () {

    }
}
