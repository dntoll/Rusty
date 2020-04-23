package Model;

import javafx.geometry.Point2D;

public class Wall {
    private final Material material;
    public Point2D start;
    public Point2D end;

    public Wall(Point2D start, Point2D end, Material mat) {
        this.start = start;
        this.end = end;
        this.material = mat;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Point2D getVector() {
        return end.subtract(start);
    }
}
