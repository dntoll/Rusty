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

        tracePrimaryLights(contact, lights, model);

       /* if (depth < 3) {
            traceSecondaryLights(contact, depth, model);
        }*/

        return model.getIntensity();
    }

    private void traceSecondaryLights(RayWorldContact contact, int depth, ShadingModel model) {
        //Reflections
        for (int i = 0; i < 10; i++) {
            Ray secondaryRay = getRay(contact.getPosition(), contact.getNormal(), Math.PI / 2.0, 1.0, i, 10);
            RayWorldContact secondaryContact = traceClosest(secondaryRay, contact.getWall());
            if (secondaryContact.hasContact()) {
                double lightIntensity = 0. * getIntensity(secondaryContact, depth + 1);
                model.getSecondaryLight(lightIntensity, secondaryContact.getPosition(), secondaryContact.getNormal(), secondaryRay.getStart());
            }

        }
    }

    private void tracePrimaryLights(RayWorldContact contact, Light[] lights, ShadingModel model) {
        for (Light light : lights ) {
            var ray = new Ray(contact.getPosition(), light.getPosition());
            if (level.lineOfSight(ray, contact.getWall())) {
                model.addDirectLight(light);
            }
        }
    }


}
