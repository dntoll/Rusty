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
}
