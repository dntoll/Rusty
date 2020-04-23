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

    @Test
    public void shouldNotCollideWithParallel() {
        var sut = new Ray(new Point2D(0,0), new Point2D(0,1));
        var wall = new Wall(new Point2D(1,0), new Point2D(1,1));

        var actual = sut.collides(wall);

        Assert.assertFalse(actual.hasContact());
    }

    @Test
    public void shouldNotCollideBeyondReach() {
        var sut = new Ray(new Point2D(0,0.5), new Point2D(1,0.5));
        var wall = new Wall(new Point2D(2,0), new Point2D(2,1));

        var actual = sut.collides(wall);

        Assert.assertFalse(actual.hasContact());
    }

    @Test
    public void shouldCollideOrthogonal() {
        var sut = new Ray(new Point2D(0,0.5), new Point2D(1,0.5));
        var wall = new Wall(new Point2D(0.5,0), new Point2D(0.5,1));

        var actual = sut.collides(wall);

        Assert.assertTrue(actual.hasContact());
    }

    @Test
    public void shouldCollideOrthogonalRayOtherSide() {
        var sut = new Ray(new Point2D(1,0.5), new Point2D(0,0.5));
        var wall = new Wall(new Point2D(0.5,0), new Point2D(0.5,1));

        var actual = sut.collides(wall);

        Assert.assertTrue(actual.hasContact());
    }

    @Test
    public void shouldCollideOrthogonalOtherSide() {
        var sut = new Ray(new Point2D(0,0.5), new Point2D(1,0.5));
        var wall = new Wall(new Point2D(0.5,1), new Point2D(0.5,0));

        var actual = sut.collides(wall);

        Assert.assertTrue(actual.hasContact());
    }
}
