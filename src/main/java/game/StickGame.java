package game;

public class StickGame {
    private int stickLength;
    private int pointLength;
    private int stickSize;
    private int pointSize;
    private int currentOwnerCount;
    private boolean[] activeStickArray;
    private String[] namesOwnerArray;
    private ReportInterface reportInterface;
    private GameConverter gameConverter;

    public StickGame(int stickLength,ReportInterface reportInterface) {
        gameConverter=new GameConverter(stickLength);
        this.reportInterface = reportInterface;
        this.stickLength = stickLength;
        pointLength = stickLength + 1;
        stickSize = 2 * stickLength * pointLength;
        currentOwnerCount = 0;
        activeStickArray = new boolean[stickSize];
        namesOwnerArray = new String[stickLength * stickLength];
        pointSize = pointLength * pointLength;
    }

    private void addStick(int number) {
        if (number < stickSize) {
            activeStickArray[number] = true;
        }
    }

    private void setOwnerCell(int number, String playerName) {
        namesOwnerArray[number] = playerName;
        reportInterface.reportOwner(number,playerName);
        currentOwnerCount++;
        reportInterface.getExtraTurn();
    }

    protected boolean isClosed(int number) {
        int column = number % stickLength;
        int row = (int) Math.ceil(number / stickLength);
        int upStick = number;
        int downStick = upStick + stickLength;
        int leftStick = pointLength * stickLength + stickLength * column + row;
        int rightStick = leftStick + stickLength;

        return (activeStickArray[upStick] && activeStickArray[downStick] && activeStickArray[leftStick] && activeStickArray[rightStick]);
    }

    private void addOwner(int stickNumber, String playerName) {
        if (stickNumber < stickLength * pointLength) {
            int upCell = stickNumber - stickLength;
            int downCell = stickNumber;
            if (upCell >= 0) {
                if (isClosed(upCell))
                    setOwnerCell(upCell, playerName);
            }
            if (downCell < stickLength * stickLength) {
                if (isClosed(downCell))
                    setOwnerCell(downCell, playerName);
            }
        } else {

            int leftCell = gameConverter.getLeftCell(stickNumber);
            int rightCell = gameConverter.getRightCell(stickNumber);
            if (leftCell != -1) {
                if (isClosed(leftCell))
                    setOwnerCell(leftCell, playerName);
            }
            if (rightCell != -1) {
                if (isClosed(rightCell))
                    setOwnerCell(rightCell, playerName);
            }
        }

    }

    public void addStick(int firstPointNumber, int secondPointNumber, String playerName) {
        int stickNumber = gameConverter.getStickNumber(firstPointNumber, secondPointNumber);
        if (stickNumber != -1) {
            addStick(stickNumber);
            reportInterface.reportEdge(firstPointNumber,secondPointNumber);
            addOwner(stickNumber, playerName);
        }
    }

    public boolean isEndGame(){
        if(currentOwnerCount==stickLength*stickLength)
            return true;
        return false;
    }

    public boolean[] getEdges(){
        return activeStickArray;
    }

}
