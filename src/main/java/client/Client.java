package client;

import server.Bridge;

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

    public Client(Bridge bridge) throws RemoteException, MalformedURLException {
        this.bridge = bridge;
        callBack = new CallBackClass();
        try {
            name = bridge.getName();
            System.out.println(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
}
