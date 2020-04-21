package Model;

import org.junit.Assert;
import org.junit.Test;

public class WorldTests {

    @Test
    public void shouldHaveLinesInLevel() {
        World sut = new World();

        Assert.assertEquals(3, sut.getLevel().getLines().length);
    }
}
