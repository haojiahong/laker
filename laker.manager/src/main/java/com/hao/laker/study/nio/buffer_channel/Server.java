package com.hao.laker.study.nio.buffer_channel;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by haojiahong on 17/5/10.
 */
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(30000);
        Socket socket = serverSocket.accept();
        Thread.sleep(1000 * 10);
        PrintStream ps = new PrintStream(socket.getOutputStream());
        ps.println("hello world");
        ps.close();
        socket.close();
    }
}
