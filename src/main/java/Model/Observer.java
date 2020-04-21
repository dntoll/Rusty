package Model;

import javafx.geometry.Point2D;

public class Observer {


    private Point2D position = new Point2D(0,0);
    private Point2D direction = new Point2D(0.75,0.75);

    public Observer() {
        direction = direction.normalize();
    }


    public Point2D getPosition() {

        return position;
    }

    public Point2D getDirection() {
        return direction;
    }
}
