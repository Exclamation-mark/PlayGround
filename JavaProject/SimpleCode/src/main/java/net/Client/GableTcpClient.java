package net.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class GableTcpClient {


    public static void main(String[] args) {
        List<String> in = Arrays.asList("sx", "asd", "vasd");
        Map<String, Object> out = new HashMap<>();
        execute(in, out);
        System.out.println(out.get("result"));
    }

    private static void execute(List<String> in, Map<String, Object> out) {
        if (in == null) {
            return;
        }
        try {
            Socket s = new Socket("127.0.0.1", 8888);
            OutputStream os = s.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            InputStream is = s.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            List<String> receive = new ArrayList<>();
            for (String str : in) {
                dos.writeUTF(str);
                String msg = dis.readUTF();
                System.out.println("收到服务端信息" + msg);
                receive.add(msg);
            }
            out.put("result", receive);
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
