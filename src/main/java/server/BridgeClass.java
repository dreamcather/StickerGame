package server;

import client.CallBack;
import game.GameSession;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class BridgeClass extends UnicastRemoteObject implements Bridge {

    private GameSession gameSession;
    private Connection connection;
    private String firstPlayer;
    private String secondPlayer;
    private HashMap<Integer,CallBack> clientMap;
    private int idCounter;


    BridgeClass(Connection connection) throws RemoteException {
        this.connection = connection;
        clientMap = new HashMap<>();
        idCounter=0;
        firstPlayer = null;
        secondPlayer=null;
    }

    public String getName() {
        if(firstPlayer==null){
            firstPlayer ="first";
            return firstPlayer;
        }
        String secondPlayer = "second";
        return secondPlayer;
    }

    public boolean addStick(int start, int second, String name) {
        return gameSession.addStick(start,second,name);
    }

    public void addClient(CallBack client) throws RemoteException {
        clientMap.put(idCounter,client);
        idCounter++;
        if(idCounter>1){
            gameSession =new GameSession(4,clientMap.get(0),clientMap.get(1));
            clientMap.get(0).createGame();
            clientMap.get(1).createGame();
            System.out.println("Game Session create");
        }
    }

    @Override
    public boolean logIn(String name, String password) {
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
