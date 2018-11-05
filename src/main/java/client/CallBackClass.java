package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackClass extends UnicastRemoteObject implements CallBack,Serializable {
    private Client client;

    public CallBackClass(String name, Client client) throws RemoteException {
        super();
        this.client = client;
    }

    public void getMessage(String string) throws RemoteException {
        System.out.println(string);
    }

    public void createGame() {
        client.createGame();
    }

    public void reportEdge(int firstPointNumber, int secondPointNumber) {
        client.reportEdge(firstPointNumber,secondPointNumber);
    }

    public void reportOwner(int number, String playerName) {
        client.reportOwner(number,playerName);
    }

}
