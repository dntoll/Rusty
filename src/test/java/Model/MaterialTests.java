package Model;

import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class MaterialTests {

    @Test
    public void shouldReturnConstructorParameters() {
        var pos = mock(Point2D.class);
        var ambient = 1.0;
        var diffuse = 2.0;
        var shininess = 3.0;
        var specular = 4.0;

        var sut = new Material(specular, diffuse, ambient, shininess);


        Assert.assertEquals(ambient, sut.getAmbient(), 0.01);
        Assert.assertEquals(diffuse, sut.getDiffuse(), 0.01);
        Assert.assertEquals(shininess, sut.getShininess(), 0.01);
        Assert.assertEquals(specular, sut.getSpecular(), 0.01);
    }
}
