package com.qezhhnjy.netty.first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author qezhhnjy
 * @date 2019/4/20-11:23
 */
public class FirstServerSocket {

    public static void main(String[] args) {
        int port = 8080;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket accept = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            PrintWriter out = new PrintWriter(accept.getOutputStream(), true);
            String req, resp = "";
            while ((req = in.readLine()) != null) {
                if ("Done".equalsIgnoreCase(req)) {
                    break;
                }
                out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
