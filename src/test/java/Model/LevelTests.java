package Model;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LevelTests {


    @Test
    public void getLinesShouldReturnEmptyLevel() {
        Level sut = new Level();

        Assert.assertEquals(0, sut.getWalls().length);
    }

    @Test
    public void getLinesShouldReturnOneLine() {
        Level sut = new Level();

        sut.addWall(mock(Wall.class));

        Assert.assertEquals(1, sut.getWalls().length);
    }
}
