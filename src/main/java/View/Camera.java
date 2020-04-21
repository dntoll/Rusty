package View;


import javafx.geometry.Point2D;

public class Camera {

    Point2D position = new Point2D(0,0);
    double scale = 1.0;

    public Point2D toLevelView(Point2D start) {
        return position.add(start.multiply(scale));
    }

    public void translate(double x, double y) {
        position = position.add(x, y);
    }

    public void scale(double newScale) {
        scale = newScale;
    }

    public double getScale() {
        return scale;
    }
}
