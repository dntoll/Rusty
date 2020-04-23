package Model;

import javafx.geometry.Point2D;

public class RayTracer {
    private final Level level;

    public RayTracer(Level level) {
        this.level = level;
    }

    public Ray getRay(Point2D position, Point2D direction, double fov, double viewDistance, int rayIndex, int numRays) {


        var dir = direction;

        //Middle ray goes through polar direction
        var polarDirection = Math.atan2(dir.getY(), dir.getX());

        //start by looking to the left
        var startRay = polarDirection + fov/2.0;
        //index by index stepping to the right
        double radiansPerRay = fov/(double)numRays;
        var rayDirection =  startRay - radiansPerRay*(double)rayIndex;

        double directionX = Math.cos(rayDirection);
        double directionY = Math.sin(rayDirection);

        var endPos = new Point2D(position.getX() + directionX * viewDistance,
                position.getY() + directionY * viewDistance );


        return new Ray(position, endPos);
    }

    public RayWorldContact traceClosest(Ray ray, Wall ignore) {

        RayWorldContact closestContact = new RayWorldContact(false, null, ray, null);

        for (Wall wall : level.getWalls() ) {
            if (wall != ignore) {
                var newContact = ray.collides(wall);
                if (newContact.hasContact()) {
                    if (newContact.isCloser(closestContact)) {
                        closestContact = newContact;
                    }
                }
            }
        }

        return closestContact;
    }

    public double getIntensity(RayWorldContact contact, int depth) {

        //https://en.wikipedia.org/wiki/Phong_reflection_model
        var material = contact.getWall().getMaterial();


        var lights = level.getLights();

        ShadingModel model = new ShadingModel(material, contact.getPosition(), contact.getNormal(), contact.getRay().getStart());

        for (Light light : lights ) {

            if (lineOfSight(contact.getPosition(), light.getPosition(), contact.getWall())) {
                model.addDirectLight(light);
            }
        }

        /*if (depth < 2) {
            //Reflections
            for (int i = 0; i < 100; i++) {
                Ray secondaryRay = getRay(contact.getPosition(), contact.getNormal(), Math.PI / 2.0, 1.0, i, 10);
                RayWorldContact secondaryContact = traceClosest(secondaryRay, contact.getWall());
                if (secondaryContact.hasContact()) {
                    double lightIntensity = 0.5 * getIntensity(secondaryContact, depth + 1);
                    model.getSecondaryLight(lightIntensity, secondaryContact.getPosition(), secondaryContact.getNormal(), secondaryRay.getStart());
                }

            }
        }*/

        return model.getIntensity();
    }

    private boolean lineOfSight(Point2D start, Point2D end, Wall ignore) {
        var ray = new Ray(start, end);
        for (Wall wall : level.getWalls() ) {
            if (wall != ignore) {
                var newContact = ray.collides(wall);
                if (newContact.hasContact()) {
                    return false;
                }
            }
        }
        return true;
    }
}
