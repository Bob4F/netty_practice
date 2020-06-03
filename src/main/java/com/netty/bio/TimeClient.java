package com.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Bob
 * @version 1.0
 * @name TimeClient
 * @desc TODO
 * @date 2020/6/3 12:48
 **/
public class TimeClient {


    public static void main(String[] args) {
        int port = 9090;
        if (args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1",port);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Query TIME ORDER");
            System.out.println("send order 2 server success");
            String resp = in.readLine();
            System.out.println("Now is :"+resp);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
