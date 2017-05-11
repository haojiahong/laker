package com.hao.laker.study.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Created by haojiahong on 2017/5/10.
 */
public class NClient {
    private Selector selector = null;
    static final int PORT = 30000;
    private Charset charset = Charset.forName("UTF-8");
    private SocketChannel socketChannel = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", PORT);
        socketChannel = SocketChannel.open(inetSocketAddress);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new ClientThread().start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            socketChannel.write(charset.encode(line));
        }


    }

    public static void main(String[] args) throws IOException {
        new NClient().init();
    }

    private class ClientThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                while (selector.select() > 0) {
                    for (SelectionKey sk : selector.selectedKeys()) {
                        selector.selectedKeys().remove(sk);
                        if (sk.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) sk.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            String content = "";
                            while (socketChannel.read(byteBuffer) > 0) {
                                byteBuffer.flip();
                                content += charset.decode(byteBuffer);
                            }
                            System.out.println("聊天信息：" + content);
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
