package game;

public class GameConverter {
    private int stickLength;
    private int pointLength;
    private int stickSize;
    private int pointSize;
    public GameConverter(int stickLength) {
        this.stickLength = stickLength;
        pointLength = stickLength + 1;
        stickSize = 2 * stickLength * pointLength;
        pointSize = pointLength * pointLength;
    }

    public int getStickNumber(int firstPointNumber, int secondPointNumber) {
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

    public int getStart(int stickNumber){
        if(stickNumber<stickLength*pointLength){
            int row = stickNumber/stickLength;
            int column =stickNumber%stickLength;
            return pointLength*row+column;
        }else {
            int number = stickNumber-pointLength*stickLength;
            int row = number%stickLength;
            int column = number/stickLength;
            return pointLength*row+column;

        }
    }

    public int getEnd(int stickNumber){
        int start = getStart(stickNumber);
        if(stickNumber<stickLength*pointLength)
            return start+1;
        return start+pointLength;
    }
}
