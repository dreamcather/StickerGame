package server;

import client.CallBack;
import client.CallBackClass;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {
    final String NAME = "Bridge";
    public String getName() throws RemoteException;
    public boolean turn(int start, int second, String name) throws RemoteException;
    public boolean[] getState() throws RemoteException;
    void addClient(CallBack client) throws RemoteException;
    boolean logIn(String name,String password)throws RemoteException;

    void addEdge(int start, int end,String name) throws RemoteException;
}
