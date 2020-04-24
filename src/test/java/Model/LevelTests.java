package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LevelTests {

    @Test
    public void getLinesShouldReturnEmptyLevel() {
        Level sut = new Level();

        Assert.assertEquals(0, sut.getWalls().length);
    }

    @Test
    public void getLinesShouldReturnOneLine() {
        Level sut = new Level();

        var added = mock(Wall.class);
        sut.addWall(added);

        Assert.assertSame(added, sut.getWalls()[0]);
    }

    @Test
    public void getLightsShouldReturnOneLine() {
        Level sut = new Level();

        var added = mock(Light.class);
        sut.addLight(added);

        Assert.assertSame(added, sut.getLights()[0]);
    }

    @Test
    public void addBoxShouldAddFourWalls() {
        Level sut = new Level();

        sut.addBox(mock(Point2D.class), mock(Point2D.class), mock(Material.class));

        Assert.assertEquals(4, sut.getWalls().length);
    }

    @Test
    public void emptyLevelHasClearVisibility() {
        Level sut = new Level();

        var actual = sut.lineOfSight(mock(Ray.class), null);

        Assert.assertTrue(actual);
    }

    @Test
    public void ignoredWallLevelHasClearVisibility() {
        Level sut = new Level();
        var onlyIgnoredWall = mock(Wall.class);
        sut.addWall(onlyIgnoredWall);

        var ray = mock(Ray.class);

        var actual = sut.lineOfSight(ray, onlyIgnoredWall);
        Assert.assertTrue(actual);
        verify(ray, never()).collides(onlyIgnoredWall);
    }

    @Test
    public void shouldMissWall() {
        Level sut = new Level();
        var onlyWall = mock(Wall.class);
        var ray = mock(Ray.class);
        var contact = mock(RayWorldContact.class);

        sut.addWall(onlyWall);
        when(ray.collides(onlyWall)).thenReturn(contact);
        when(contact.hasContact()).thenReturn(false);


        var actual = sut.lineOfSight(ray, null);
        Assert.assertTrue(actual);
        verify(ray, atMostOnce()).collides(onlyWall);
    }

    @Test
    public void shouldHitWall() {
        Level sut = new Level();
        var onlyWall = mock(Wall.class);
        var ray = mock(Ray.class);
        var contact = mock(RayWorldContact.class);

        sut.addWall(onlyWall);
        when(ray.collides(onlyWall)).thenReturn(contact);
        when(contact.hasContact()).thenReturn(true);


        var actual = sut.lineOfSight(ray, null);
        Assert.assertFalse(actual);
        verify(ray, atMostOnce()).collides(onlyWall);
    }
}
