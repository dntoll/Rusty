package Model;


import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Level {
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<Light> lights = new ArrayList<Light>();

    public Level() {

    }

    public void addWall(Wall toBeAdded) {
        walls.add(toBeAdded);
    }

    public void addLight(Light toBeAdded) {
        lights.add(toBeAdded);
    }

    public Wall[] getWalls() {
        return (Wall[]) this.walls.toArray(new Wall[0]);
    }

    public Light[] getLights() { return this.lights.toArray(new Light[0]); }

    public void addBox(Point2D topLeft, Point2D bottomRight, Material material) {
        var bottomLeft = new Point2D(topLeft.getX(),bottomRight.getY());
        var topRight = new Point2D(bottomRight.getX(),topLeft.getY());

        addWall(new Wall(topLeft, topRight, material));
        addWall(new Wall(topLeft, bottomLeft, material));
        addWall(new Wall(bottomLeft, bottomRight, material));
        addWall(new Wall(topRight, bottomRight, material));
    }

    public boolean lineOfSight(Ray ray, Wall ignore) {

        for (Wall wall : getWalls() ) {
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
