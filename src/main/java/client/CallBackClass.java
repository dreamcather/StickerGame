package client;

import java.io.Serializable;
import java.rmi.RemoteException;

public class CallBackClass implements CallBack,Serializable {
    private String name;
    public void getMessage(String string) throws RemoteException {
        System.out.println(string);
    }

    public String getName() {
        return name;
    }
}
