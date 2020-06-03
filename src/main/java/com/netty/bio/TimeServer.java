package com.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Bob
 * @version 1.0
 * @name TimeServer
 * @desc 同步阻塞IO TimeServer
 * @date 2020/6/3 12:00
 **/
public class TimeServer {

    public static void main(String[] args) throws IOException {
        int port = 9090;
        if (args!=null && args.length>0){
            try {
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:"+port);
            Socket socket =null;
            while (true){
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }finally {
            System.out.println("the time server close");
            server.close();
        }
    }

}
