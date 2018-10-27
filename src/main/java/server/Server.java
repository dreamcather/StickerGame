package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static void main(final String[] args) throws IOException, AlreadyBoundException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:Server.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        BridgeClass bridge = new BridgeClass(connection);
        Registry localReg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        localReg.bind(bridge.NAME,bridge);
        System.out.println("Server run");

    }
}