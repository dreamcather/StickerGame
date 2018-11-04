package server;

import client.CallBackClass;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {
    final String NAME = "Bridge";
    public String getName() throws RemoteException;
    public boolean turn(int start, int second, String name) throws RemoteException;
    public boolean[] getState() throws RemoteException;
    void addClient(CallBackClass client) throws RemoteException;
    boolean logIn(String name,String password)throws RemoteException;
}
