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
}
