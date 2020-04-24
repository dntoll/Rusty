package Model;

import javafx.geometry.Point2D;

public class RayWorldContact {
    private final boolean hasContact;
    private final Point2D contactPosition;
    private final Ray ray;
    private final Wall wall;

    public RayWorldContact(boolean hasContact, Point2D contactPosition, Ray ray, Wall wall) {
        this.hasContact = hasContact;
        this.contactPosition = contactPosition;
        this.ray = ray;
        this.wall = wall;
    }

    public boolean hasContact() {
        return hasContact;
    }

    public Point2D getPosition() {
        return contactPosition;
    }

    public boolean isCloser(RayWorldContact other) {

        if (hasContact() == false) {
            return false;
        }

        if (other.hasContact() == true) {
            var otherContactStartPosition = other.getRay().getStart();
            var distanceForThisContact = this.ray.getStart().distance(contactPosition);
            var distanceForOtherContact = otherContactStartPosition.distance(other.contactPosition);

            return distanceForThisContact <= distanceForOtherContact;
        } else {
            return true;
        }
    }

    public Ray getRay() { //Must show this to the test
        return this.ray;
    }

    public Wall getWall() {
        return this.wall;
    }

    public Point2D getNormal() {

        var lineVector = this.wall.getVector();

        var lineNormal1 = new Point2D(lineVector.getY(), -lineVector.getX());
        var lineNormal2 = new Point2D(-lineVector.getY(), lineVector.getX());

        if (this.ray.getVector().dotProduct(lineNormal1) < 0) //on this side
            return lineNormal1.normalize();
        else
            return lineNormal2.normalize();
    }

    public Point2D getReflection() {
        //https://www.gamedev.net/forums/topic/510581-2d-reflection/
        var N = getNormal();
        var V = ray.getVector();
        var vdn = 2.0 * V.dotProduct(N);
        var temp = N.multiply(vdn);

        return V.subtract(temp);
    }
}
