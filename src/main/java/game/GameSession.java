package game;

import client.CallBack;

import java.rmi.RemoteException;

public class GameSession implements ReportInterface {
    private CallBack firstPlayer;
    private CallBack secondPlayer;
    private StickGame stickGame;
    private String currentPlayer;
    private boolean extraTurn;

    public GameSession(int count, CallBack firstPlayer, CallBack secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        currentPlayer ="first";
        extraTurn = false;
        stickGame = new StickGame(count,this);
    }

    private boolean isMyTurn(String name){
        if(name.equals(currentPlayer))
            return true;
        return false;
    }

    private void changeTurn(){
        if(!extraTurn) {
            if (currentPlayer.equals("first"))
                currentPlayer = "second";
            else {
                currentPlayer = "first";
            }
        }
        else{
            extraTurn=false;
        }
    }

    public void getExtraTurn(){
        extraTurn = true;
    }

    public synchronized boolean addStick(int firstPointNumber, int secondPointNumber, String name){
        if(isMyTurn(name)){
            stickGame.addStick(firstPointNumber,secondPointNumber,name);
            changeTurn();
            return true;
        }
        return false;
    }

    public void reportEdge(int firstPointNumber,int secondPointNumber){
        try {
            firstPlayer.reportEdge(firstPointNumber,secondPointNumber);
            secondPlayer.reportEdge(firstPointNumber,secondPointNumber);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean[] getStation(){
        return stickGame.getEdges();
    }

    public void reportOwner(int number, String playerName) {
        try {
            firstPlayer.reportOwner(number,playerName);
            secondPlayer.reportOwner(number,playerName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
