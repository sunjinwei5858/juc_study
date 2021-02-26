package com.sunjinwei.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 阻塞IO：
 */
public class BlockingIO {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress("host", 6267));
        int idx = 0;
        while (true) {
            /**
             * 该accept方法是阻塞的
             */
            final Socket socket = ss.accept();
            new Thread(() -> {
                handler(socket);
            }, "").start();
        }
    }

    private static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        String msg = "aaaa";
        try {
            socket.getOutputStream().write(msg.getBytes());
            socket.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
