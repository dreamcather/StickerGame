package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bridge extends Remote {
    String NAME = "Bridge";

    String getName() throws RemoteException;

    boolean turn(int start, int second, String name) throws RemoteException;

    boolean[] getState() throws RemoteException;
}
