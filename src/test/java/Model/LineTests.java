package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class LineTests {

    @Test
    public void ConstructorShouldSetStartAndEnd() {

        var start = new Point2D(0.0,1.0);
        var end = new Point2D(2.0,3.0);
        var sut = new Line(start, end);

        Assert.assertSame(start, sut.start);
        Assert.assertSame(end, sut.end);
    }
}
