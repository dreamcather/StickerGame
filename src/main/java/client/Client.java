package client;

import server.Bridge;

import java.rmi.RemoteException;

public class Client {
    String name;
    Bridge bridge;

    public Client(Bridge bridge) {
        this.bridge = bridge;
        try {
            name = bridge.getName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
