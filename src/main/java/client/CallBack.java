package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBack extends Remote {
    final String NAME ="CallBack";
    public void getMessage(String string)throws RemoteException;

}
