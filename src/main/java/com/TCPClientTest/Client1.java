package com.TCPClientTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("n58770595y.zicp.fun", 29494);
        OutputStream os = s.getOutputStream();
        os.write(6);
        new Thread(new ClientSendMsg(s)).start();
        new Thread(new ClientReceiveMsg(s)).start();
    }
}
