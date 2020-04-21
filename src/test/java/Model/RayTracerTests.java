package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RayTracerTests {

    @Test
    public void rayShouldBeTop() {
        Level level = mock(Level.class);
        Observer o = new Observer();
        o.setDirection(new Point2D(1,0));
        o.setPosition(new Point2D(0,0));

        RayTracer sut = new RayTracer(level);

        Ray actual = sut.getRay(o, Math.PI, 1,0, 10);


        Assert.assertEquals(0.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(1.0, actual.getEnd().getY(), 0.1);
    }

    @Test
    public void rayShouldBeBottom() {
        Level level = mock(Level.class);
        Observer o = new Observer();
        o.setDirection(new Point2D(1,0));
        o.setPosition(new Point2D(0,0));

        RayTracer sut = new RayTracer(level);

        Ray actual = sut.getRay(o, Math.PI, 1,10, 10);


        Assert.assertEquals(0.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(-1.0, actual.getEnd().getY(), 0.1);
    }

    @Test
    public void rayShouldBeRight() {
        Level level = mock(Level.class);
        Observer o = new Observer();
        o.setDirection(new Point2D(1,0));
        o.setPosition(new Point2D(0,0));

        RayTracer sut = new RayTracer(level);

        Ray actual = sut.getRay(o, Math.PI, 1,5, 10);


        Assert.assertEquals(1.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(0.0, actual.getEnd().getY(), 0.1);
    }


}
