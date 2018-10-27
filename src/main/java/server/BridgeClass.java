package server;

import client.CallBack;
import client.CallBackClass;
import game.GameSession;
import game.StickGame;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.ArrayList;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private GameSession gameSession;
    private Connection connection;
    String firstPlayer;
    String secondPlayer;
    CallBack firstClient;


    protected BridgeClass(Connection connection) throws RemoteException {
        this.connection = connection;
        gameSession = new GameSession(4,"first","second");
    }

    public String getName() throws RemoteException {
        if(firstPlayer==null){
            firstPlayer ="first";
            try {
                firstClient = (CallBack) Naming.lookup("Client");
                firstClient.getMessage("Fuck");
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
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
