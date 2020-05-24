import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;


public class MyClinent {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter out =null;
        BufferedReader in = null;
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        //ui 启动
        MyRunnable ui = new MyRunnable();
        ui.showUI();
    }

}
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("通过Runnable创建的线程!");

    }
    public void trysocket(){
        Socket socket = null;
        BufferedWriter out =null;
        BufferedReader in = null;
        try{
            socket = new Socket("localhost",9999);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write("有朋自远方来");
            out.newLine();
            out.flush();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String recvStr = in.readLine();
            System.out.println("客户端收到的数据是:"+recvStr);
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            if(out != null)
                out.close();
            if(!socket.isClosed())
                socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void showUI () {
        JFrame jFrame = new JFrame("客户器端");
        jFrame.setBounds(300, 300, 630, 300);
        jFrame.getContentPane().setLayout(new BorderLayout());
        JPanel jpane1 = new JPanel();
        JPanel jpane2 = new JPanel();
        //组件
        JButton jButton1 = new JButton("启动服务器");


        //-----窗体添加-------

        jpane1.add(jButton1);
        /*---增加监听事件------*/

        jButton1.addActionListener(new OuterClass(this));
        //-----窗体结构-------
        javax.swing.border.Border titleBorder1=BorderFactory.createTitledBorder("服务器启动面板");
        jpane1.setBorder(titleBorder1);
        jFrame.getContentPane().add(jpane1, BorderLayout.PAGE_START);
        jFrame.getContentPane().add(jpane2, BorderLayout.CENTER);
        jFrame.setVisible(true);
        //jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public class OuterClass implements ActionListener {
        MyRunnable ui;
        public OuterClass(MyRunnable ui) {
            this.ui = ui;
        }
        public void actionPerformed(ActionEvent e) {
            MyRunnable sock = new MyRunnable();
            sock.trysocket();
            System.out.println("点击了按钮");
        }
        public void mousechick(ActionEvent e){

        }
    }

}