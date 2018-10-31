package server;

import game.GameSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private GameSession gameSession;
    private ArrayList<String> accounts;
    private ArrayList<MessageMail> mails;
    String firstPlayer;
    String secondPlayer;

    protected BridgeClass() throws RemoteException {
        accounts = new ArrayList<>();
        mails = new ArrayList<>();
        gameSession = new GameSession(4,"first","second");
    }

    public int addName(String name) throws RemoteException {
        if(!isExist(name)){
            accounts.add(name);
            mails.add(new MessageMail(name));
            return 0;
        }
        return -1;
    }

    public boolean isExist(String name) throws RemoteException {
        if(accounts.indexOf(name)==-1)
            return false;
        return true;
    }

    public ArrayList<String> getNameList() throws RemoteException {
        return accounts;
    }

    public String getName() throws RemoteException {
        if(firstPlayer==null){
            firstPlayer ="first";
            return firstPlayer;
        }
        secondPlayer = "second";
        return secondPlayer;
    }

    public boolean turn(int start, int second, String name) throws RemoteException {
        return gameSession.addStick(start,second,name);
    }

    public boolean[] getState() throws RemoteException {
        return gameSession.getStation();
    }

    @Override
    public String[] getOwner() throws RemoteException {
        return gameSession.getOwner();
    }

    @Override
    public int getOwnerCount() throws RemoteException {
        return gameSession.getOwnerCount();
    }

    @Override
    public int getCurentEdgeCount() throws RemoteException {
        return gameSession.getCurrentEdgeCount();
    }


}
