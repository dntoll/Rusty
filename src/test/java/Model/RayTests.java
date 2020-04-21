package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class RayTests {


    @Test
    public void endShouldBeInsertedInConstructor() {
        var expected = new Point2D(1,1);
        var sut = new Ray(new Point2D(0,0), expected);

        Assert.assertSame(expected, sut.getEnd());
    }

    @Test
    public void startShouldBeInsertedInConstructor() {
        var expected = new Point2D(0,0);
        var sut = new Ray(expected, new Point2D(1,1));

        Assert.assertSame(expected, sut.getStart());
    }
}
