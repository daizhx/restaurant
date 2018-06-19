package com.smtech.restaurant.common;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

//探测Server地址
public class ServerDiscover {
    private volatile String serverIP;
    //尝试次数
    private AtomicInteger detectTimes = new AtomicInteger(0);

    private DatagramSocket detectSocket;

    private Thread tReceive = new Thread(){

        @Override
        public void run() {
            System.out.println("Receive thread started.");
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf,buf.length);
            try {
                detectSocket.setSoTimeout(10*1000);
                detectSocket.receive(packet);
            } catch (SocketTimeoutException e){
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }

            serverIP = ((InetSocketAddress)packet.getSocketAddress()).getHostString();
            System.out.println("serverIP=" + serverIP);
            String rcvd = "Received from " + packet.getSocketAddress() + ", Data="
                    + new String(packet.getData(), 0, packet.getLength());
            System.out.println(rcvd);
            detectTimes.set(3);
        }
    } ;

    public void execute(int port){
        // Use this port to send broadcast packet
        try {
            detectSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        tReceive.start();
        // Send packet thread
        System.out.println("Send thread started.");
        int packetPort = Constants.UDP_PORT;
        // Broadcast address
        InetAddress hostAddress;
        try {
            hostAddress = InetAddress.getByName("192.168.1.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }
        String outMessage = "restaurant server";
        byte[] buf = outMessage.getBytes();
        // Send packet to hostAddress:9999, server that listen
        // 9999 would reply this packet
        DatagramPacket out = new DatagramPacket(buf, buf.length, hostAddress, packetPort);
        while (detectTimes.intValue() < 3) {
            try {
                System.out.println("Send " + outMessage + " to " + hostAddress);
                detectSocket.send(out);
                //等待3秒
                Thread.sleep(1000*3);
                detectTimes.addAndGet(1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    // 调用execute后，获取ServerIP的方法
    public String getServerIP() {
        return serverIP;
    }
}
