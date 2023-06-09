package com.TCPClientTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiveMsg implements Runnable {
    private Socket s;

    public ClientReceiveMsg(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            receiveMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMsg() throws IOException {
        BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String data;
        while ((data = brClient.readLine()) != null) {
            System.out.println(data);
        }
    }
}
