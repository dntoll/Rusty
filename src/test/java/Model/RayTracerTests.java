package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RayTracerTests {

    private Observer o;
    private RayTracer sut;
    private Level level;
    private Point2D direction, position;

    Ray hittingRay;
    RayWorldContact firstContactHit, secondContactHit, contactMiss;
    Wall firstWall, secondWall;

    @Before
    public void setup() {
        level = mock(Level.class);

        firstWall = mock(Wall.class);
        secondWall = mock(Wall.class);
        when(level.getWalls()).thenReturn(new Wall[]{firstWall, secondWall});


        direction = new Point2D(1,0);
        position = new Point2D(0,0);

        sut = new RayTracer(level);
        hittingRay = mock(Ray.class);

        firstContactHit = mock(RayWorldContact.class);
        when(firstContactHit.hasContact()).thenReturn(true);
        when(firstContactHit.isCloser(any())).thenReturn(true);

        secondContactHit = mock(RayWorldContact.class);
        when(secondContactHit.hasContact()).thenReturn(true);

        contactMiss = mock(RayWorldContact.class);
        when(contactMiss.hasContact()).thenReturn(false);


    }


    @Test
    public void rayStartShouldBeObserverPosition() {
        Ray actual = sut.getRay(position, direction, Math.PI, 1,0, 10);
        Assert.assertEquals(0.0, actual.getStart().getX(), 0.01);
        Assert.assertEquals(0.0, actual.getStart().getY(), 0.01);
    }

    @Test
    public void rayShouldBeTop() {
        Ray actual = sut.getRay(position, direction, Math.PI, 1,0, 10);
        Assert.assertEquals(0.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(1.0, actual.getEnd().getY(), 0.1);
    }

    @Test
    public void rayShouldBeBottom() {
        Ray actual = sut.getRay(position, direction, Math.PI, 1,10, 10);
        Assert.assertEquals(0.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(-1.0, actual.getEnd().getY(), 0.1);
    }

    @Test
    public void rayShouldBeRight() {
        Ray actual = sut.getRay(position, direction, Math.PI, 1,5, 10);
        Assert.assertEquals(1.0, actual.getEnd().getX(), 0.1);
        Assert.assertEquals(0.0, actual.getEnd().getY(), 0.1);
    }

    @Test
    public void traceShouldHitFirstWall() {
        when(hittingRay.collides(firstWall)).thenReturn(firstContactHit);
        when(hittingRay.collides(secondWall)).thenReturn(contactMiss);

        when(secondContactHit.isCloser(any())).thenReturn(false);

        var actual = sut.traceClosest(hittingRay, null);

        Assert.assertSame(firstContactHit, actual);
    }

    @Test
    public void traceShouldHitSecondWallSinceItsCloser() {
        when(hittingRay.collides(firstWall)).thenReturn(firstContactHit);
        when(hittingRay.collides(secondWall)).thenReturn(secondContactHit);

        when(firstContactHit.isCloser(any())).thenReturn(false);
        when(secondContactHit.isCloser(any())).thenReturn(true);


        var actual = sut.traceClosest(hittingRay, null);

        Assert.assertSame(secondContactHit, actual);
    }

    @Test
    public void traceShouldHitFirstWallSinceItsCloser() {
        when(hittingRay.collides(firstWall)).thenReturn(firstContactHit);
        when(hittingRay.collides(secondWall)).thenReturn(secondContactHit);

        when(secondContactHit.isCloser(any())).thenReturn(false);
        when(firstContactHit.isCloser(any())).thenReturn(true);

        var actual = sut.traceClosest(hittingRay, null);

        Assert.assertSame(firstContactHit, actual);
    }

    @Test
    public void traceShouldMiss() {

        when(hittingRay.collides(firstWall)).thenReturn(contactMiss);
        when(hittingRay.collides(secondWall)).thenReturn(contactMiss);

        var actual = sut.traceClosest(hittingRay, null);
        Assert.assertFalse(actual.hasContact());
    }

    @Test
    public void shouldTryAllWalls() {

    }




}
