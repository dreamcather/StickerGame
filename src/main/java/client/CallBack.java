package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBack extends Remote {

    void createGame() throws RemoteException;

    void reportEdge(int firstPointNumber, int secondPointNumber) throws RemoteException;

    void reportOwner(int number, String playerName) throws RemoteException;
}
