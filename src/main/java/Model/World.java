package Model;

import javafx.geometry.Point2D;

public class World {
    Level level = new Level();
    Observer observer = new Observer();


    public World() {
        var middle = new Point2D(0.5,0.5);
        var rightMiddle = new Point2D(1,0.5);

        var leftMiddle = new Point2D(0,0.5);
        var bottomMiddle = new Point2D(0.5,1);

        var material = new Material(1,1,1,10);
        level.addBox(new Point2D(0.0,0.0), new Point2D(1.0,1.0), material);

        level.addBox(new Point2D(0.75,0.25), new Point2D(0.85,0.3), material);
        level.addCircle(new Point2D(0.75,0.7), 0.05, 60, material);


        level.addLight(new Light(new Point2D(0.1, 0.8), 0.0,1.3,0.3, 2));
    }


    public Level getLevel() {
        return level;
    }

    public Observer getObserver() {
        return observer;
    }
}
