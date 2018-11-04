package server;

import client.CallBack;
import client.CallBackClass;
import game.GameSession;
import game.StickGame;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private GameSession gameSession;
    private Connection connection;
    String firstPlayer;
    String secondPlayer;
    private HashMap<Integer,CallBackClass> clientMap;
    private int idCounter;
    CallBack firstClient;


    protected BridgeClass(Connection connection) throws RemoteException {
        this.connection = connection;
        clientMap = new HashMap<>();
        idCounter=0;
        firstPlayer = null;
        secondPlayer=null;
    }

    public String getName() throws RemoteException {
        if(firstPlayer==null){
            firstPlayer ="first";
            try {
                firstClient = (CallBack) Naming.lookup("Client");
                firstClient.getMessage("Fuck");
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return firstPlayer;
        }
        String secondPlayer = "second";
        return secondPlayer;
    }

    public boolean turn(int start, int second, String name) throws RemoteException {
        return gameSession.addStick(start,second,name);
    }

    public boolean[] getState() throws RemoteException {
        return gameSession.getStation();
    }

    public void addClient(CallBackClass client) throws RemoteException {
        clientMap.put(idCounter,client);
        idCounter++;
        if(idCounter>1){
            gameSession =new GameSession(4,clientMap.get(0),clientMap.get(1));
        }
    }

    @Override
    public boolean logIn(String name, String password) throws RemoteException {
        String sql = "SELECT * FROM User WHERE NAME = ? AND PASSWORD = ?";
        ResultSet rs =null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2,password);
            rs=pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            rs.next();
            if(!rs.wasNull())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
