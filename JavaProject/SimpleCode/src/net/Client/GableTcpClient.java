package net.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GableTcpClient {


    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            int c = 0;
            while(c++ < 6){
                Scanner sc = new Scanner(System.in);
                String str = sc.next();
                dos.writeUTF(str);
                String msg = dis.readUTF();
                System.out.println("收到服务端信息"+msg);
            }
            is.close();
            os.close();
            s.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
