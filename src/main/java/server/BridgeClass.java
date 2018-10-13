package server;

import game.StickGame;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private StickGame stickGame;
    String firstPlayer;
    String secondPlayer;

    protected BridgeClass() throws RemoteException {
        stickGame = new StickGame(4);
    }

    public String getName() throws RemoteException {
        if(firstPlayer==null){
            firstPlayer ="first";
            return firstPlayer;
        }
        secondPlayer = "second";
        return secondPlayer;
    }
}
