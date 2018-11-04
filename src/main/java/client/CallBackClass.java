package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackClass extends UnicastRemoteObject implements CallBack,Serializable {
    private String name;
    private Client client;

    public CallBackClass(String name, Client client) throws RemoteException {
        super();
        this.name = name;
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

    @Override
    public void reportOwner(int number, String playerName) {
        client.reportOwner(number,playerName);
    }

    public String getName() {
        return name;
    }
}
