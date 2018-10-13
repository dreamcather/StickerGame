package game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StickGameTest {
    @Test
    public void getStickNumberTest1() {
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getStickNumber(5, 10);
        int expected = 21;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStickNumberTest2() {
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getStickNumber(18, 17);
        int expected = 14;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStickNumberTest3() {
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getStickNumber(21, 17);
        int expected = -1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void isClosedTest1() {
        StickGame stickGame = new StickGame(4);
        stickGame.addStick(11, 12, null);
        stickGame.addStick(11, 16, null);
        stickGame.addStick(12, 17, null);
        stickGame.addStick(16, 17, null);

        Assert.assertTrue(stickGame.isClosed(9));
    }

    @Test
    public void isClosedTest2() {
        StickGame stickGame = new StickGame(4);
        stickGame.addStick(5, 10, null);
        stickGame.addStick(11, 6, null);
        stickGame.addStick(11, 10, null);
        stickGame.addStick(6, 5, null);

        Assert.assertTrue(stickGame.isClosed(4));
    }

    @Test
    public void isClosedTest3() {
        StickGame stickGame = new StickGame(4);
        stickGame.addStick(5, 10, null);
        stickGame.addStick(11, 6, null);
        stickGame.addStick(11, 10, null);
        stickGame.addStick(6, 5, null);

        Assert.assertFalse(stickGame.isClosed(5));
    }

    @Test
    public void getLeftCellTest1() {
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getLeftCell(30);
        int expected = 9;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getLeftCellTest2() {
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getLeftCell(25);
        int expected = 4;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void addStickTest1() {
        StickGame stickGame = new StickGame(4);
        stickGame.addStick(11, 12, null);
        stickGame.addStick(11, 16, null);
        stickGame.addStick(12, 17, null);
        stickGame.addStick(16, 17, null);

        int actual = stickGame.getCurrentOwnerCount();
        int expected = 1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStartTest1(){
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getStart(14);
        int expected = 17;

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getStartTest2(){
        StickGame stickGame = new StickGame(4);

        int actual = stickGame.getStart(37);
        int expected = 9;

        Assert.assertEquals(actual,expected);
    }
}
