package client;

import server.Bridge;

import java.rmi.RemoteException;
import java.util.ArrayList;

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

    public boolean isExist(String name){
        try {
            return  bridge.isExist(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addName(String name){
        this.name =name;
        try {
            bridge.addName(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getNameList(){
        ArrayList<String> res =new ArrayList<>();
        try {
            res=bridge.getNameList();
            res.remove(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return res;
    }
}
