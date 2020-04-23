package Model;

import javafx.geometry.Point2D;

public class Ray {
    private final Point2D start;
    private final Point2D end;

    public Ray(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;

    }

    public Point2D getEnd() {
        return end;
    }

    public Point2D getStart() {
        return start;
    }

    public RayWorldContact collides(Wall w) {
        var ret = lineIntersection(w.start, w.end, start, end);
        if (ret == null) {
            return new RayWorldContact(false, null, this, w);
        } else {
            return new RayWorldContact(true, ret, this, w);
        }
    }

    //https://github.com/processing/processing/wiki/Line-Collision-Detection
    private Point2D lineIntersection(Point2D l1p1, Point2D l1p2, Point2D l2p1, Point2D l2p2) {
        double x1 = l1p1.getX();
        double y1 = l1p1.getY();
        double x2 = l1p2.getX();
        double y2 = l1p2.getY();
        double x3 = l2p1.getX();
        double y3 = l2p1.getY();
        double x4 = l2p2.getX();
        double y4 = l2p2.getY();

        // calculate the distance to intersection point
        final double v = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        double uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / v;
        double uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / v;

        // if uA and uB are between 0-1, lines are colliding
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            return new Point2D(x1 + (uA * (x2-x1)), y1 + (uA * (y2-y1)));
        }
        return null;
    }

    public Point2D getVector() {
        return end.subtract(start);
    }
}
