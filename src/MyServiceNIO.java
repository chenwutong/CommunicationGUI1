import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MyServiceNIO {
    public static void main(String[] args) throws Exception {
        Selector selector;
        ServerSocketChannel listenChannel = null;
        SocketAddress remoteAddr = new InetSocketAddress("127.0.0.1", 9999);
        Selector select = Selector.open();
        listenChannel = ServerSocketChannel.open();
        listenChannel.socket().bind(remoteAddr);
        listenChannel.configureBlocking(false);//设置阻塞方法非阻塞
        listenChannel.register(select, SelectionKey.OP_ACCEPT);
        while (true) {
            if (select.select(600) == 0) {//阻塞
                System.out.println("独自等待.");
                continue;
            }
            select.select();
            Set<SelectionKey> readkeys = select.selectedKeys();//获取客户端返回的set of keys

            Iterator iterator = readkeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();

                if (key.isAcceptable()) {
                    SocketChannel client = ((ServerSocketChannel) key.channel()).accept();
                    System.out.println("Accept connection from: " + "" + client);
                    client.configureBlocking(false);
                    client.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isWritable()) {
                    System.out.println("write data...");

                }
                if (key.isReadable()) {
                    System.out.println("Read data...");
                }
                key.channel().close();
            }
        }
    }
}
