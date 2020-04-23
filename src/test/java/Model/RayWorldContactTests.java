package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RayWorldContactTests {

    RayWorldContact other;
    Point2D otherContactPosMock;
    Point2D contactPosMock;
    Point2D startPosMock;
    Ray rayMock;

    @Before
    public void setUp() {
        this.other = mock(RayWorldContact.class);
        this.otherContactPosMock = mock(Point2D.class);
        this.contactPosMock = mock(Point2D.class);

        this.rayMock = mock(Ray.class);
        this.startPosMock = mock(Point2D.class);

        when(rayMock.getStart()).thenReturn(startPosMock);
        when(other.getRay()).thenReturn(rayMock);
        when(other.getPosition()).thenReturn(otherContactPosMock);

    }

    @Test
    public void hasContactThroughConstructor() {
        var sut = new RayWorldContact(true, null, null, null);
        Assert.assertTrue(sut.hasContact());

        var sut2 = new RayWorldContact(false, null, null, null);
        Assert.assertFalse(sut2.hasContact());
    }

    @Test
    public void getPositionThroughConstructor() {
        var pos = new Point2D(1, 0);
        var sut = new RayWorldContact(true, pos, null, null);
        Assert.assertSame(pos, sut.getPosition());
    }

    @Test
    public void getWallThroughConstructor() {
        var wall = new Wall(null, null);
        var sut = new RayWorldContact(true, null, null, wall);
        Assert.assertSame(wall, sut.getWall());
    }


    @Test
    public void isCloserShouldReturnTrue() {
        when(startPosMock.distance(contactPosMock)).thenReturn(0.0); //mine is closer
        when(startPosMock.distance(otherContactPosMock)).thenReturn(1.0); //further away
        when(other.hasContact()).thenReturn(true);

        var sut = new RayWorldContact(true, contactPosMock, rayMock, null);

        var actual = sut.isCloser(other);

        Assert.assertTrue(actual);
    }

    @Test
    public void isCloserShouldReturnFalse() {
        when(startPosMock.distance(contactPosMock)).thenReturn(1.0); //mine is further
        when(startPosMock.distance(otherContactPosMock)).thenReturn(0.0); //other is closer
        when(other.hasContact()).thenReturn(true);

        var sut = new RayWorldContact(true, contactPosMock, rayMock, null);

        var actual = sut.isCloser(other);

        Assert.assertFalse(actual);
    }

    @Test
    public void isCloserReturnsTrueIfOtherHasNoContact() {
        when(other.hasContact()).thenReturn(false);

        var sut = new RayWorldContact(true, contactPosMock, rayMock, null);

        var actual = sut.isCloser(other);

        Assert.assertTrue(actual);
    }

    @Test
    public void isCloserReturnsFalseIfThisHasNoContact() {
        when(other.hasContact()).thenReturn(false);

        var sut = new RayWorldContact(false, contactPosMock, rayMock, null);

        var actual = sut.isCloser(other);

        Assert.assertFalse(actual);
    }

}
