
import javax.swing.*;



import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerUI extends JFrame{
    static Socket socket = null;
    static ServerSocket serverSock = null;
    static BufferedReader br = null;
    static BufferedWriter bw = null;
    static int clientCounts = 0;

    public ServerUI(){

    }
    private static Object object;

    /**
     * Launch the application.

     */
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("服务器端");
        jFrame.setBounds(300, 300, 630, 300);
        jFrame.getContentPane().setLayout(new BorderLayout());
        JPanel jpanel = new JPanel();
        JPanel jpane2 = new JPanel();
        jpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel jlabel1 = new JLabel("服务器主机名：");
        JTextField jTextField = new JTextField(10);
        JLabel jlabel2 = new JLabel("服务器端口号：");
        JTextField jTextField1 = new JTextField(10);
        JButton jButton1 = new JButton("启动服务器");
        JTextArea textArea = new JTextArea(11, 50);
        textArea.setBorder(BorderFactory.createEtchedBorder());// 线边框
        JScrollPane scrollPane = new JScrollPane(textArea);
        //---增加监听事件------

        //jButton1.addActionListener(new MyServer.OuterClass(this));
        //jFrame.addMouseListener(new outerMouseListener());
        //jTextArea.addKeyListener(new outerKeyListener());
        //---增加界面窗体----
        jpanel.add(jlabel1);
        jpanel.add(jTextField);
        jpanel.add(jlabel2);
        jpanel.add(jTextField1);
        jpanel.add(jButton1);

        jpane2.add(textArea);
        jButton1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    serverSock = new ServerSocket(9999);
                    textArea.append("服务器开始连接\n");
                    //socket = serverSock.accept();//阻塞
                    System.out.println("服务器连接成功");
                    new Thread(() -> {
                        try {
                            while(true) {
                                socket = serverSock.accept();//阻塞
                                System.out.println("服务器连接成功");
                                clientCounts++;
                                textArea.append("客户机编号" + clientCounts + "会话开始\n");
                                //文本-》字符流-》字节流
                                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while(true) {
                                            String recvStr = null; // 服务器端接收
                                            try {
                                                recvStr = br.readLine();
                                                textArea.append("服务器接收到的数据是:" + recvStr + "\n");
                                                bw.write(recvStr);// 服务器端发送
                                                bw.newLine();
                                                bw.flush();
                                            } catch (IOException ex) {
                                                ex.printStackTrace();
                                            }
                                        }
                                    }
                                }).start();

                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ;
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
        //addMouseListener add = new addMouseListener();

        //-----窗体结构-------
        jFrame.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if(br!=null) br.close();
                    if(serverSock!=null) serverSock=null;
                    if(socket!=null) socket=null;
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }//asdf

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        javax.swing.border.Border titleBorder1=BorderFactory.createTitledBorder("服务器启动面板");
        jpanel.setBorder(titleBorder1);
        jFrame.getContentPane().add(jpanel, BorderLayout.PAGE_START);
        jFrame.getContentPane().add(jpane2, BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
