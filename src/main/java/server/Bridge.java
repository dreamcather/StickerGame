package server;

import client.CallBack;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bridge extends Remote {
    String NAME = "Bridge";
    String getName() throws RemoteException;
    void addClient(CallBack client) throws RemoteException;
    boolean logIn(String name,String password)throws RemoteException;

    boolean addStick(int start, int end, String name) throws RemoteException;
}
