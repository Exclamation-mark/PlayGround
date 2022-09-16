package net.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class GableTcpServer {

    private static final class Handle implements Runnable {
        Socket s = null;
        int id = 0;

        public Handle(Socket s, int id) {
            this.s = s;
            this.id = id;
        }

        @Override
        public void run() {
            if (s == null) {
                return;
            }
            try {
                System.out.println("accept a request, id: " + id + " created");
                InputStream is = s.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                OutputStream os = s.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                while (true) {
                    try {
                        String msg = dis.readUTF();
                        System.out.println("socket connection " + id + " receive msg:" + msg);
                        dos.writeUTF("Hello " + msg);
                    } catch (Exception e) {
                        System.out.println("socket connection " + id + "  closed");
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("Server listening on: 8888");
            Socket s = null;
            int count = 0;
            while ((s = ss.accept()) != null) {
                new Thread(new Handle(s, count++)).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
