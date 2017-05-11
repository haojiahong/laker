package com.hao.laker.study.nio.nio_book;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Description:
 * <br/>ÍøÕ¾: <a href="http://www.crazyit.org">·è¿ñJavaÁªÃË</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 *
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class NClient {
    // ¶¨Òå¼ì²âSocketChannelµÄSelector¶ÔÏó
    private Selector selector = null;
    static final int PORT = 30000;
    // ¶¨Òå´¦Àí±àÂëºÍ½âÂëµÄ×Ö·û¼¯
    private Charset charset = Charset.forName("UTF-8");
    // ¿Í»§¶ËSocketChannel
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        // µ÷ÓÃopen¾²Ì¬·½·¨´´½¨Á¬½Óµ½Ö¸¶¨Ö÷»úµÄSocketChannel
        sc = SocketChannel.open(isa);
        // ÉèÖÃ¸ÃscÒÔ·Ç×èÈû·½Ê½¹¤×÷
        sc.configureBlocking(false);
        // ½«SocketChannel¶ÔÏó×¢²áµ½Ö¸¶¨Selector
        sc.register(selector, SelectionKey.OP_READ);
        // Æô¶¯¶ÁÈ¡·þÎñÆ÷¶ËÊý¾ÝµÄÏß³Ì
        new ClientThread().start();
        // ´´½¨¼üÅÌÊäÈëÁ÷
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            // ¶ÁÈ¡¼üÅÌÊäÈë
            String line = scan.nextLine();
            // ½«¼üÅÌÊäÈëµÄÄÚÈÝÊä³öµ½SocketChannelÖÐ
            sc.write(charset.encode(line));
        }
    }

    // ¶¨Òå¶ÁÈ¡·þÎñÆ÷Êý¾ÝµÄÏß³Ì
    private class ClientThread extends Thread {
        public void run() {
            try {
                while (selector.select() > 0)    // ¢Ù
                {
                    // ±éÀúÃ¿¸öÓÐ¿ÉÓÃIO²Ù×÷Channel¶ÔÓ¦µÄSelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        // É¾³ýÕýÔÚ´¦ÀíµÄSelectionKey
                        selector.selectedKeys().remove(sk);
                        // Èç¹û¸ÃSelectionKey¶ÔÓ¦µÄChannelÖÐÓÐ¿É¶ÁµÄÊý¾Ý
                        if (sk.isReadable()) {
                            // Ê¹ÓÃNIO¶ÁÈ¡ChannelÖÐµÄÊý¾Ý
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while (sc.read(buff) > 0) {
                                sc.read(buff);
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            // ´òÓ¡Êä³ö¶ÁÈ¡µÄÄÚÈÝ
                            System.out.println("ÁÄÌìÐÅÏ¢£º" + content);
                            // ÎªÏÂÒ»´Î¶ÁÈ¡×÷×¼±¸
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new NClient().init();
    }
}
