package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class WallTests {

    @Test
    public void ConstructorShouldSetStartAndEnd() {

        var start = new Point2D(0.0,1.0);
        var end = new Point2D(2.0,3.0);
        var sut = new Wall(start, end, mock(Material.class));

        Assert.assertSame(start, sut.start);
        Assert.assertSame(end, sut.end);
    }
}
