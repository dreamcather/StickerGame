package client;

import server.Bridge;

import java.rmi.RemoteException;

public class Client {
    String name;
    Bridge bridge;

    public Client(Bridge bridge) {
        this.bridge = bridge;
        try {
            name = bridge.getName();
            System.out.println(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
