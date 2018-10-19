package server;

import game.GameSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private GameSession gameSession;
    String firstPlayer;
    String secondPlayer;

    protected BridgeClass() throws RemoteException {
        gameSession = new GameSession(4,"first","second");
    }

    public String getName() throws RemoteException {
        if(firstPlayer==null){
            firstPlayer ="first";
            return firstPlayer;
        }
        secondPlayer = "second";
        return secondPlayer;
    }

    public boolean turn(int start, int second, String name) throws RemoteException {
        return gameSession.addStick(start,second,name);
    }

    public boolean[] getState() throws RemoteException {
        return gameSession.getStation();
    }


}
