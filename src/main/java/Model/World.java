package Model;

import javafx.geometry.Point2D;

public class World {
    Level level = new Level();
    Observer observer = new Observer();


    public World() {
        var start = new Point2D(0.5,0);
        var end = new Point2D(1,0);
        var three = new Point2D(1,1);
        level.addLine(new Line(start, end));
        level.addLine(new Line(three, end));
        level.addLine(new Line(start, three));
    }


    public Level getLevel() {
        return level;
    }

    public Observer getObserver() {
        return observer;
    }
}
