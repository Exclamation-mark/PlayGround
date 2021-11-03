package contest.thread;


import contest.listener.ReceiveListener;

public class FivesTimer extends Thread {
    private int n = 0;
    private ReceiveListener receiveListener;
    public void setReceiveListener(ReceiveListener receiveListener) {
        this.receiveListener = receiveListener;
    }
    public void setN(int n) {this.n = n;}

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * n);
                this.send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void send() {

        receiveListener.receiveMessage();
    }
}