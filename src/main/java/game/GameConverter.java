package game;

public class GameConverter {
    private int stickLength;
    private int pointLength;
    private int stickSize;
    private int pointSize;
    private int currentActiveStick;
    private int currentOwnerCount;
    private boolean[] activeStickArray;
    private String[] namesOwnerArray;
    public GameConverter(int stickLength) {
        this.stickLength = stickLength;
        pointLength = stickLength + 1;
        stickSize = 2 * stickLength * pointLength;
        currentActiveStick = 0;
        currentOwnerCount = 0;
        activeStickArray = new boolean[stickSize];
        namesOwnerArray = new String[stickLength * stickLength];
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
}
