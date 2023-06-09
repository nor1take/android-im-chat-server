package com.TCPClientTest;

import java.io.*;
import java.net.Socket;

public class ClientSendMsg implements Runnable {
    private Socket s;

    public ClientSendMsg(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            sendMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //*封装输出流对象：new OutputStreamWriter(s.getOutputStream())把字节输出流转化为字符流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        //如果是输入不是886，发送数据
        String line;
        while ((line = br.readLine()) != null) {
            if ("886".equals(line)) {
                break;
            }
            //获取输出流对象，写数据
            //OutputStream os = s.getOutputStream();
            //os.write(line.getBytes());
            /*能不能一次写一个字符串呢？
             *我们获取到的流是字节流，一次写一个字符串需要字符流
             *写数据的方式变为：*/
            bw.write("#25@3用户:" + line);
            bw.newLine();
            bw.flush();
        }
    }
}
