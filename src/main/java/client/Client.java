package client;

import server.Bridge;
import visualGame.VisualStickGame;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    String name;
    Bridge bridge;
    CallBack callBack;
    VisualStickGame visualStickGame;
    ClientGUI clientGUI;

    public Client(Bridge bridge,VisualStickGame visualStickGame,ClientGUI clientGUI) throws RemoteException, MalformedURLException {
        this.visualStickGame =visualStickGame;
        this.bridge = bridge;
        this.clientGUI =clientGUI;
        try {
            name = bridge.getName();
            System.out.println(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        callBack = new CallBackClass(name,this);
        bridge.addClient(callBack);
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

    public boolean[] getStation(){
        try {
            return bridge.getState();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEdge(int start,int end){
        try {
            bridge.addEdge(start,end,name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
