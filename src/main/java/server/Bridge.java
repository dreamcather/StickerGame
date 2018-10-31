package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {
    String NAME = "Bridge";

    public int addName(String name) throws RemoteException;

    public boolean isExist(String name) throws RemoteException;

    public ArrayList<String> getNameList() throws RemoteException;

    String getName() throws RemoteException;

    boolean turn(int start, int second, String name) throws RemoteException;

    boolean[] getState() throws RemoteException;

    String[] getOwner() throws RemoteException;

    int getOwnerCount() throws RemoteException;

    int getCurentEdgeCount() throws RemoteException;
}
