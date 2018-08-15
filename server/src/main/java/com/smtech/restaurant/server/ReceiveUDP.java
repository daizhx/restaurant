package com.smtech.restaurant.server;

import com.smtech.restaurant.common.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//用户监听UDP广播包的线程
public class ReceiveUDP extends Thread {

    @Override
    public void run() {
        int listenPort = Constants.UDP_PORT;
        byte[] buf = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        DatagramSocket responseSocket;
        try {
            responseSocket = new DatagramSocket(listenPort);
        } catch (SocketException e) {
            e.printStackTrace();
            //异常返回
            return;
        }
        System.out.println("serverstarted");
        while (true){
            try {
                responseSocket.receive(packet);
                String rcvd = "Received " + new String(packet.getData(), 0, packet.getLength())
                        + " from address: " + packet.getSocketAddress();
                System.out.println(rcvd);

                // Send a response packet to sender
                String backData = "DCBA";
                byte[] data = backData.getBytes();
                System.out.println("Send " + backData + " to " + packet.getSocketAddress());
                DatagramPacket backPacket = new DatagramPacket(data, 0, data.length, packet.getSocketAddress());
                responseSocket.send(backPacket);
            } catch (IOException e) {
                e.printStackTrace();
                //异常退出
                break;
            }
        }
    }
}
