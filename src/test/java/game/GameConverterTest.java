package game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GameConverterTest {
    @Test
    public void getStickNumberTest1() {
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getStickNumber(5, 10);
        int expected = 21;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStickNumberTest2() {
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getStickNumber(18, 17);
        int expected = 14;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStickNumberTest3() {
        GameConverter gameConverter= new GameConverter(4);

        int actual = gameConverter.getStickNumber(21, 17);
        int expected = -1;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getLeftCellTest1() {
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getLeftCell(30);
        int expected = 9;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getLeftCellTest2() {
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getLeftCell(25);
        int expected = 4;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getLeftCellTest3() {
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getLeftCell(29);
        int expected = 5;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getStartTest1(){
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getStart(14);
        int expected = 17;

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getStartTest2(){
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getStart(37);
        int expected = 9;

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getEndTest1(){
        GameConverter gameConverter = new GameConverter(4);

        int actual = gameConverter.getEnd(37);
        int expected = 14;

        Assert.assertEquals(actual,expected);
    }

}
