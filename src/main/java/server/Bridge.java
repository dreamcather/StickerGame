package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Bridge extends Remote {
    final String NAME = "Bridge";
    public String getName() throws RemoteException;
}
