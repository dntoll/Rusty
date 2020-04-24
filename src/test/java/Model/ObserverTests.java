package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class ObserverTests {
    @Test
    public void shouldGetTheSetPosition() {
        Observer sut = new Observer();

        double x = 0.5;
        double y = 1.0;
        sut.setPosition(new Point2D(x, y)); // middle


        Assert.assertEquals(x, sut.getPosition().getX(), 0.01);
        Assert.assertEquals(y, sut.getPosition().getY(), 0.01);
    }

    @Test
    public void shouldGetTheSetDirectionButNormalized() {
        Observer sut = new Observer();

        double x = 0.5;
        double y = 1.0;

        var p = new Point2D(x, y);
        sut.setDirection(p); // middle
        var expected = p.normalize();

        Assert.assertEquals(expected, sut.getDirection());
    }
}
