package game;

public class GameSession {
    String firstPlayer;
    String secondPlayer;
    StickGame stickGame;
    String curentPlayer;

    public GameSession(int count,String firstPlayer, String secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        curentPlayer = firstPlayer;
        stickGame = new StickGame(count);
    }

    private boolean isMyTurn(String name){
        if(name.equals(curentPlayer))
            return true;
        return false;
    }

    private void changeTurn(){
        if(curentPlayer.equals(firstPlayer))
            curentPlayer = secondPlayer;
        else
            curentPlayer =firstPlayer;
    }

    public boolean addStick(int firstPointNumber, int secondPointNumber, String name){
        if(isMyTurn(name)){
            int start =stickGame.getClossedCellCount();
            stickGame.addStick(firstPointNumber,secondPointNumber,name);
            int end = stickGame.getClossedCellCount();
            if(start==end) {
                changeTurn();
            }
            return true;
        }
        return false;
    }

    public boolean[] getStation(){
        return stickGame.getEdges();
    }

    public String[] getOwner(){
        return stickGame.getNamesOwnerArray();
    }

    public int getOwnerCount(){
        return stickGame.getCurrentOwnerCount();
    }

    public int getCurrentEdgeCount(){
        return stickGame.getCurrentActiveStick();
    }
}
