package com.smtech.restaurant.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

//业务启动对象
public class StartUp {

    // 探测服务端的IP
    public void start() throws Exception{
        // Use this port to send broadcast packet
        @SuppressWarnings("resource")
        final DatagramSocket detectSocket = new DatagramSocket(8888);

        // Send packet thread
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Send thread started.");
                while (true) {
                    try {
                        byte[] buf = new byte[1024];
                        int packetPort = 9999;
                        // Broadcast address
                        InetAddress hostAddress = InetAddress.getByName("192.168.184.255");
                        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
                        String outMessage = stdin.readLine();
                        if (outMessage.equals("bye"))
                            break;
                        buf = outMessage.getBytes();
                        System.out.println("Send " + outMessage + " to " + hostAddress);

                        // Send packet to hostAddress:9999, server that listen
                        // 9999 would reply this packet
                        DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, packetPort);
                        detectSocket.send(out);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        }).start();
    }
}
