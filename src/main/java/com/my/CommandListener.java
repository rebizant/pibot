package com.my;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class CommandListener  implements Runnable{

    private  Connection connection;
    private String userName;
    private String password;
    private String admin;
    private final int MAX_TRY_COUNTER = 10;

    public CommandListener(String userName, String password, String admin){
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    private void createConnection()  {

        boolean conCreated = false;
        int tryCounter = 0;
        while(!conCreated){
             try{
                tryCounter++;
                ConnectionConfiguration conf = new ConnectionConfiguration("talk.google.com",5222,"gmail");
                connection = new XMPPConnection(conf);
                connection.connect();
                connection.login(userName,password);
                conCreated = true;
            }   catch (Exception e){
                e.printStackTrace();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            if(tryCounter >= MAX_TRY_COUNTER){
                throw new RuntimeException("too many tries");
            }


        }

    }

    @Override
    public void run() {

           createConnection();
           connection.addPacketListener(new PacketListener() {
               @Override
               public void processPacket(Packet packet) {
                   if(packet instanceof Message){
                       Message msg = (Message)packet;
                       if(msg.getType() == Message.Type.chat && msg.getBodies() != null && msg.getBody() != null){
                           System.out.println("message from admin "+msg.getBody());
                       }
                   }
               }
           }, new FromMatchesFilter(admin));

    }
}
