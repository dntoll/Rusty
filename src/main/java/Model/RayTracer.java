package Model;

import javafx.geometry.Point2D;

public class RayTracer {
    public RayTracer(Level level) {
    }

    public Ray getRay(Observer o, double fov, double viewDistance, int left, int numRays) {

        double radiansPerRay = fov/(double)numRays;
        var dir = o.getDirection();
        var polarDirection = Math.atan2(dir.getY(), dir.getX()) +fov/2.0 -radiansPerRay*(double)left;

        double directionX = Math.cos(polarDirection);
        double directionY = Math.sin(polarDirection);

        var endPos = new Point2D(o.getPosition().getX() + directionX * viewDistance,
                                 o.getPosition().getY() + directionY * viewDistance );


        return new Ray(o.getPosition(), endPos);
    }

    public RayWorldContact trace(Ray ray) {
        return new RayWorldContact();
    }
}
