package game;

public class StickGame {
    private int stickLength;
    private int pointLength;
    private int stickSize;
    private int pointSize;
    private int currentActiveStick;
    private boolean[] activeStickArray;
    private String[] namesOwnerArray;

    public StickGame(int stickLength) {
        this.stickLength = stickLength;
        pointLength = stickLength + 1;
        stickSize = 2 * stickLength * pointLength;
        currentActiveStick = 0;
        activeStickArray = new boolean[stickSize];
        namesOwnerArray = new String[stickLength * stickLength];
        pointSize = pointLength * pointLength;
    }

    private void addStick(int number) {
        if (number < stickSize) {
            if (activeStickArray[number] = true) ;
        }
    }

    protected int getStickNumber(int firstPointNumber, int secondPointNumber) {
        if ((firstPointNumber > pointSize) || (secondPointNumber > pointSize))
            return -1;
        if ((firstPointNumber < 0) || (secondPointNumber < 0))
            return -1;

        int start = Math.min(firstPointNumber, secondPointNumber);
        int end = Math.max(firstPointNumber, secondPointNumber);

        int startColumn = start % pointLength;
        int endColumn = end % pointLength;
        int startRow = (int) Math.ceil(start / pointLength);
        int endRow = (int) Math.ceil(end / pointLength);

        if ((Math.abs(endColumn - startColumn) == 1) && (startRow == endRow)) {
            return startRow * stickLength + startColumn;
        }
        if ((Math.abs(endRow - startRow) == 1) && (startColumn == endColumn)) {
            return stickLength * pointLength + startColumn * stickLength + startRow;
        }
        return -1;

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

    protected int getLeftCell(int stickNumber) {
        int number = stickNumber - pointLength * stickLength;
        if (number < 0)
            return -1;
        if (number < stickLength)
            return -1;
        return stickLength * (number / stickLength) + number % stickLength - 1;
    }

    protected int getRightCell(int stickNumber) {
        int leftCell = getLeftCell(stickNumber);
        if (leftCell % stickLength == 3)
            return -1;
        return leftCell + 1;
    }

    private void addOwner(int stickNumber, String playerName) {
        if (stickNumber < stickLength * pointLength) {
            int upCell = stickNumber - stickLength;
            int downCell = stickNumber;
            if (upCell >= 0) {
                if (isClosed(upCell))
                    namesOwnerArray[upCell] = playerName;
            }
            if (downCell < stickLength * stickLength) {
                if (isClosed(downCell))
                    namesOwnerArray[downCell] = playerName;
            }
        } else {

            int leftCell = getLeftCell(stickNumber);
            int rightCell = getRightCell(stickNumber);
            if (leftCell != -1) {
                if (isClosed(leftCell))
                    namesOwnerArray[leftCell] = playerName;
            }
            if (rightCell != -1) {
                if (isClosed(rightCell))
                    namesOwnerArray[rightCell] = playerName;
            }
        }

    }

    public boolean[] getActiveStickArray() {
        return activeStickArray;
    }

    public void addStick(int firstPointNumber, int secondPointNumber, String playerName) {
        int stickNumber = getStickNumber(firstPointNumber, secondPointNumber);
        if (stickNumber != -1) {
            if (!isClosed(stickNumber))
                addStick(stickNumber);
            addOwner(stickNumber,playerName);
        }
    }

}
