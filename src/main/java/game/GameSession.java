package game;

import client.CallBack;
import client.CallBackClass;

public class GameSession {
    CallBack firstPlayer;
    CallBack secondPlayer;
    StickGame stickGame;
    String curentPlayer;

    public GameSession(int count, CallBack firstPlayer, CallBack secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        curentPlayer  =null;
        stickGame = new StickGame(count);
    }

    private boolean isMyTurn(String name){
        if(name.equals(curentPlayer))
            return true;
        return false;
    }

    private void changeTurn(){
    }

    public boolean addStick(int firstPointNumber, int secondPointNumber, String name){
        if(isMyTurn(name)){
            stickGame.addStick(firstPointNumber,secondPointNumber,name);
            changeTurn();
            return true;
        }
        return false;
    }

    public boolean[] getStation(){
        return stickGame.getEdges();
    }
}
