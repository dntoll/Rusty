package Model;

import org.junit.Assert;
import org.junit.Test;

public class WorldTests {

    @Test
    public void shouldHaveLinesInLevel() {
        World sut = new World();

        Assert.assertTrue(sut.getLevel().getWalls().length > 0);
        Assert.assertTrue(sut.getLevel().getLights().length > 0);
        Assert.assertTrue(sut.getObserver() != null);
    }
}
