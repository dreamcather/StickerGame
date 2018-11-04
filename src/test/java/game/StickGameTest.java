package game;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StickGameTest {

    @Test
    public void isClosedTest1(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(6,7,null);
        stickGame.addStick(11,12,null);
        stickGame.addStick(6,11,null);
        stickGame.addStick(7,12,null);

        Assert.assertTrue(stickGame.isClosed(5));

    }

    @Test
    public void isClosedTest2(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(16,17,null);
        stickGame.addStick(17,22,null);
        stickGame.addStick(22,21,null);
        stickGame.addStick(21,16,null);

        Assert.assertTrue(stickGame.isClosed(13));

    }

    @Test
    public void isClosedTest3(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(16,17,null);
        stickGame.addStick(17,22,null);
        stickGame.addStick(22,21,null);
        stickGame.addStick(21,14,null);

        Assert.assertFalse(stickGame.isClosed(13));

    }

    @Test
    public void isClosedTest4(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(11,16,null);
        stickGame.addStick(17,12,null);
        stickGame.addStick(12,11,null);
        stickGame.addStick(16,17,null);

        Assert.assertTrue(stickGame.isClosed(9));

    }

    @Test
    public void addStickTest1(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(17,18,null);
        stickGame.addStick(18,13,null);
        stickGame.addStick(13,12,null);
        stickGame.addStick(12,17,null);

        stickGame.addStick(13,8,null);
        stickGame.addStick(8,7,null);
        stickGame.addStick(7,12,null);

        Assert.assertTrue(stickGame.isClosed(10));

    }

    @Test
    public void addStickTest2(){
        StickGame stickGame = new StickGame(4,new EmptyReport());
        stickGame.addStick(17,18,null);
        stickGame.addStick(18,13,null);
        stickGame.addStick(13,12,null);
        stickGame.addStick(12,17,null);

        stickGame.addStick(13,8,null);
        stickGame.addStick(8,7,null);
        stickGame.addStick(7,12,null);

        Assert.assertTrue(stickGame.isClosed(6));

    }
}
