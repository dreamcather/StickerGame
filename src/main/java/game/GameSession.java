package game;

import client.CallBackClass;

public class GameSession {
    CallBackClass firstPlayer;
    CallBackClass secondPlayer;
    StickGame stickGame;
    String curentPlayer;

    public GameSession(int count,CallBackClass firstPlayer, CallBackClass secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        curentPlayer = firstPlayer.getName();
        stickGame = new StickGame(count);
    }

    private boolean isMyTurn(String name){
        if(name.equals(curentPlayer))
            return true;
        return false;
    }

    private void changeTurn(){
        if(curentPlayer.equals(firstPlayer))
            curentPlayer = secondPlayer.getName();
        else
            curentPlayer =firstPlayer.getName();
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
