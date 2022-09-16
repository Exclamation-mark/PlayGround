package contest.controller;

import contest.listener.ReceiveListener;
import contest.thread.FivesTimer;

import java.text.SimpleDateFormat;

public class Receive {
    private static SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
    private FivesTimer fivesTimer;
    public Receive() {
        this.fivesTimer = new FivesTimer();
        this.fivesTimer.setN(5);
        fivesTimer.setReceiveListener(new ReceiveListener() {
            @Override
            public void receiveMessage() {
                System.out.println("5 seconds"+"   当前时间:"+sdf.format(System.currentTimeMillis()));
            }
        });
    }
    public void startSend() {this.fivesTimer.start();}

    public static void main(String[] args) {
        System.out.println(sdf.format(System.currentTimeMillis()));
        Receive r = new Receive();
        r.startSend();
    }
}
