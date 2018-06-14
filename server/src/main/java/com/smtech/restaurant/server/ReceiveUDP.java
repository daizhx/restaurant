package com.smtech.restaurant.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ReceiveUDP extends Thread {

    @Override
    public void run() {
        int listenPort = 9877;
        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        try {
            DatagramSocket responseSocket = new DatagramSocket(listenPort);


        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
