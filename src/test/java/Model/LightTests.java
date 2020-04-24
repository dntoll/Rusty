package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LightTests {

    @Test
    public void shouldGetConstructorParameters() {
        var pos = mock(Point2D.class);
        var ambient = 1.0;
        var diffuse = 2.0;
        var shininess = 3.0;
        var attenuation = 4.0;

        var sut = new Light(pos, ambient, diffuse, shininess, attenuation);

        Assert.assertSame(sut.getPosition(), pos);
        Assert.assertEquals(ambient, sut.getAmbient(), 0.01);
        Assert.assertEquals(diffuse, sut.getDiffuse(), 0.01);
        Assert.assertEquals(shininess, sut.getShininess(), 0.01);
        Assert.assertEquals(attenuation, sut.getAttenuation(), 0.01);
    }
}
