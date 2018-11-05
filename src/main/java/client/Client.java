package client;

import server.Bridge;
import visualGame.VisualStickGame;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements CallBack,Serializable {
    String name;
    Bridge bridge;
    VisualStickGame visualStickGame;
    ClientGUI clientGUI;

    public Client(Bridge bridge,ClientGUI clientGUI) throws RemoteException, MalformedURLException {
        this.bridge = bridge;
        this.clientGUI =clientGUI;
        try {
            name = bridge.getName();
            System.out.println(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        bridge.addClient(this);
    }

    public boolean logIn(String name, String password){
        try {
            return bridge.logIn(name,password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean turn(int start, int end){
        try {
            return bridge.turn(start,end,name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void getMessage(String string) throws RemoteException {

    }

    public void createGame() {
        clientGUI.createGame();
    }

    public void reportEdge(int firstPointNumber, int secondPointNumber) {
        visualStickGame.addEdge(firstPointNumber,secondPointNumber);
    }

    public void setVisualGame(VisualStickGame visualStickGame) {
        this.visualStickGame = visualStickGame;
    }

    public void reportOwner(int number, String playerName) {
        if(name.equals(playerName)) {
            visualStickGame.addOwner(number, true);
        }
        else {
            visualStickGame.addOwner(number, false);
        }
    }
}
