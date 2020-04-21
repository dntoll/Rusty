package Model;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LevelTests {


    @Test
    public void getLinesShouldReturnEmptyLevel() {
        Level sut = new Level();

        Assert.assertEquals(0, sut.getLines().length);
    }

    @Test
    public void getLinesShouldReturnOneLine() {
        Level sut = new Level();

        sut.addLine(mock(Line.class));

        Assert.assertEquals(1, sut.getLines().length);
    }
}
