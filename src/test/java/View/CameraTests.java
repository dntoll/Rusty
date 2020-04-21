package View;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;


public class CameraTests {
    @Test
    public void shouldTranslate() {
        var sut = new Camera();
        sut.translate(10, 0);


        var input = new Point2D(10,10);
        var actual = sut.toLevelView(input);
        var expected = new Point2D(20, 10);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldScale() {
        var sut = new Camera();
        sut.scale(10.0);


        var input = new Point2D(10,5);
        var actual = sut.toLevelView(input);
        var expected = new Point2D(100, 50);

        Assert.assertEquals(expected, actual);
    }

}
