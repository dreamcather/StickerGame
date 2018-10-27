package client;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CallBackClass implements CallBack,Serializable {

    @Override
    public void getMessage(String string) throws RemoteException {
        System.out.println(string);
    }
}
