package visualGame;

import client.Client;

public class RefreshLoop extends Thread{
    VisualStickGame visualStickGame;
    Client client;

    public RefreshLoop(VisualStickGame visualStickGame, Client client) {
        this.visualStickGame = visualStickGame;
        this.client = client;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(1000);
                if(visualStickGame.getCurentEdgeCount()!=client.getStation().length)
                visualStickGame.refresh(client.getStation());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
