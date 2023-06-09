package com.TCPServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Server {
    static Map<String, Socket> clientList = new HashMap<>();
    static Map<Socket, String> clientList2 = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9999);
        while (true) {
            Socket s = ss.accept();
            BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            int uid = brClient.read();

            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("已连接TCP服务器");
            bwServer.newLine();
            bwServer.flush();

            String uidUser = uid + "用户";
            if (clientList.containsKey(uidUser)) {
                clientList2.remove(clientList.get(uidUser));
            }
            clientList.put(uidUser, s);
            clientList2.put(s, uidUser);

            System.out.println(new Date() + " >>> " + uidUser + "/" + s + " LOG IN");

            new Thread(new ServerThread(s)).start();
        }
    }

    public static class ServerThread implements Runnable {
        private Socket s;

        public ServerThread(Socket s) {
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

        /**
         * line格式：
         * 4用户#72@1用户:1
         *
         * msg格式：
         * 4用户#postId@1用户:content
         */

        private void receiveMsg() throws IOException {
            try {
                BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String line;
                while ((line = brClient.readLine()) != null) {
                    String msg = clientList2.get(s) + line;
                    System.out.println(new Date() + " >>> " + msg);
                    sendMsg(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendMsg(String msg) throws IOException {
            Map<String, Socket> tempMap = new HashMap<>(clientList);
            String[] split = msg.split("#", 2); // 0 = sender ; 1 = postId@uidUser:content
            String[] split1 = split[1].split("@", 2); // 0 = postId ; 1 = uidUser:content
            for (Map.Entry<String, Socket> entry : tempMap.entrySet()) {
                if (split1[1].startsWith(entry.getKey()))
                    try {
                        BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(entry.getValue().getOutputStream()));
                        bwServer.write(msg);
                        bwServer.newLine();
                        bwServer.flush();
                    } catch (IOException e) {
                        clientList.remove(entry.getKey());
                        clientList2.remove(entry.getValue());
                        System.out.println(entry.getKey() + " 已下线");
                    }
            }
        }
    }
}
