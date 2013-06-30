package com.my;

import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            props.load(Main.class.getResourceAsStream("/conf.properties"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Main main = new Main();

//        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable runnable) {
//                Thread thr = new Thread(runnable);
//                thr.setDaemon(true);
//                return thr;
//            }
//        });

        CommandListener commandListener = new CommandListener(props.getProperty("username"), props.getProperty("password"), props.getProperty("admin"));
        commandListener.run();

        synchronized (main){
            try {
                main.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private static void test() {
        ConnectionConfiguration conf = new ConnectionConfiguration("talk.google.com", 5222, "gmail.com");
        Connection con = new XMPPConnection(conf);

        try {
            con.connect();
            con.login("pibot123@gmail.com", "barracuda6");

            ChatManager chatManager = con.getChatManager();
            Chat chat = chatManager.createChat("rebizant@gmail.com", new MessageListener() {
                @Override
                public void processMessage(Chat chat, Message message) {
                    if (message.getBodies() != null && message.getBodies().size() > 0) {

                        System.out.println(message.getBody());
                    }
                }
            });

            chat.sendMessage("to jest wiadomosc z bota");
            Thread.sleep(200000);
            con.disconnect();


        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
