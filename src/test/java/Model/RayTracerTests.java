package Model;

import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RayTracerTests {

    @Test
    public void getTraceShouldHasEmptyTraceForEmptyWorld() {
        Level level = mock(Level.class);
        Observer o = mock(Observer.class);

        RayTracer sut = new RayTracer(level);

        RayWorldContact actual = sut.getTrace(o, 1, 0, 10);

        Assert.assertFalse(actual.hasContact());
    }
}
