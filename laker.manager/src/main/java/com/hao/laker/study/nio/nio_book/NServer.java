package com.hao.laker.study.nio.nio_book;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

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
public class NServer {
    // ÓÃÓÚ¼ì²âËùÓÐChannel×´Ì¬µÄSelector
    private Selector selector = null;
    static final int PORT = 30000;
    // ¶¨ÒåÊµÏÖ±àÂë¡¢½âÂëµÄ×Ö·û¼¯¶ÔÏó
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();
        // Í¨¹ýopen·½·¨À´´ò¿ªÒ»¸öÎ´°ó¶¨µÄServerSocketChannelÊµÀý
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", PORT);
        // ½«¸ÃServerSocketChannel°ó¶¨µ½Ö¸¶¨IPµØÖ·
        server.bind(isa);
        // ÉèÖÃServerSocketÒÔ·Ç×èÈû·½Ê½¹¤×÷
        server.configureBlocking(false);
        // ½«server×¢²áµ½Ö¸¶¨Selector¶ÔÏó
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (selector.select() > 0) {
            // ÒÀ´Î´¦ÀíselectorÉÏµÄÃ¿¸öÒÑÑ¡ÔñµÄSelectionKey
            for (SelectionKey sk : selector.selectedKeys()) {
                // ´ÓselectorÉÏµÄÒÑÑ¡ÔñKey¼¯ÖÐÉ¾³ýÕýÔÚ´¦ÀíµÄSelectionKey
                selector.selectedKeys().remove(sk);      // ¢Ù
                // Èç¹ûsk¶ÔÓ¦µÄChannel°üº¬¿Í»§¶ËµÄÁ¬½ÓÇëÇó
                if (sk.isAcceptable())        // ¢Ú
                {
                    // µ÷ÓÃaccept·½·¨½ÓÊÜÁ¬½Ó£¬²úÉú·þÎñÆ÷¶ËµÄSocketChannel
                    SocketChannel sc = server.accept();
                    // ÉèÖÃ²ÉÓÃ·Ç×èÈûÄ£Ê½
                    sc.configureBlocking(false);
                    // ½«¸ÃSocketChannelÒ²×¢²áµ½selector
                    sc.register(selector, SelectionKey.OP_READ);
                    // ½«sk¶ÔÓ¦µÄChannelÉèÖÃ³É×¼±¸½ÓÊÜÆäËûÇëÇó
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                // Èç¹ûsk¶ÔÓ¦µÄChannelÓÐÊý¾ÝÐèÒª¶ÁÈ¡
                if (sk.isReadable())     // ¢Û
                {
                    // »ñÈ¡¸ÃSelectionKey¶ÔÓ¦µÄChannel£¬¸ÃChannelÖÐÓÐ¿É¶ÁµÄÊý¾Ý
                    SocketChannel sc = (SocketChannel) sk.channel();
                    // ¶¨Òå×¼±¸Ö´ÐÐ¶ÁÈ¡Êý¾ÝµÄByteBuffer
                    ByteBuffer buff = ByteBuffer.allocate(1024);
                    String content = "";
                    // ¿ªÊ¼¶ÁÈ¡Êý¾Ý
                    try {
                        while (sc.read(buff) > 0) {
                            buff.flip();
                            content += charset.decode(buff);
                        }
                        // ´òÓ¡´Ó¸Ãsk¶ÔÓ¦µÄChannelÀï¶ÁÈ¡µ½µÄÊý¾Ý
                        System.out.println("¶ÁÈ¡µÄÊý¾Ý£º" + content);
                        // ½«sk¶ÔÓ¦µÄChannelÉèÖÃ³É×¼±¸ÏÂÒ»´Î¶ÁÈ¡
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    // Èç¹û²¶×½µ½¸Ãsk¶ÔÓ¦µÄChannel³öÏÖÁËÒì³££¬¼´±íÃ÷¸ÃChannel
                    // ¶ÔÓ¦µÄClient³öÏÖÁËÎÊÌâ£¬ËùÒÔ´ÓSelectorÖÐÈ¡ÏûskµÄ×¢²á
                    catch (IOException ex) {
                        // ´ÓSelectorÖÐÉ¾³ýÖ¸¶¨µÄSelectionKey
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }
                    // Èç¹ûcontentµÄ³¤¶È´óÓÚ0£¬¼´ÁÄÌìÐÅÏ¢²»Îª¿Õ
                    if (content.length() > 0) {
                        // ±éÀú¸ÃselectorÀï×¢²áµÄËùÓÐSelectionKey
                        for (SelectionKey key : selector.keys()) {
                            // »ñÈ¡¸Ãkey¶ÔÓ¦µÄChannel
                            Channel targetChannel = key.channel();
                            // Èç¹û¸ÃchannelÊÇSocketChannel¶ÔÏó
                            if (targetChannel instanceof SocketChannel) {
                                // ½«¶Áµ½µÄÄÚÈÝÐ´Èë¸ÃChannelÖÐ
                                SocketChannel dest = (SocketChannel) targetChannel;
                                dest.write(charset.encode(content));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args)
            throws IOException {
        new NServer().init();
    }
}
