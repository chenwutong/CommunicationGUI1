
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import javax.swing.*;

public class MyServer extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static void main(String[] args)throws IOException {
        //初始化
        Socket socket = null;
        ServerSocket serverSocket = null;
        BufferedReader br = null;
        PrintStream ps= null;
        //开始连接
        //Runnable runnable = new MyRunnable(this);
        //Thread thread = new Thread(runnable);


        while(true){
            serverSocket = new ServerSocket(9999);
            System.out.println("----------服务器端暴露----------");
            socket = serverSocket.accept();
            System.out.println("----------程序执行完毕----------");
            //读取客户端的数据
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();
            System.out.println("服务器收到的数据是："+str);

            //服务器向客户端写入数据
            ps=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            ps.println("不亦乐乎");
            ps.flush();
            ps.close();
            br.close();

        }

    }
    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("通过Runnable创建的线程!");
            MyServer ui = new MyServer();
            ui.showUI();
        }
    }
    public void showUI () {
        //----添加组件---Component/组件


    }



    public class OuterClass implements ActionListener {
        MyServer ui;
        public OuterClass(MyServer ui) {
            this.ui = ui;
        }
        public void actionPerformed(ActionEvent e) {
            System.out.println("点击了按钮");
        }
        public void mousechick(ActionEvent e){


        }
    }


    public class outerKeyListener implements KeyListener{
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyChar());
        }

        /**
         * Invoked when a key has been pressed.
         */
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyChar());
        }

        /**
         * Invoked when a key has been released.
         */
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyChar());
        }
    }

}

