package Model;

import javafx.geometry.Point2D;

public class Observer {


    private Point2D position = new Point2D(0.1,0.5);
    private Point2D direction = new Point2D(1,0);

    public Observer() {
        direction = direction.normalize();
    }


    public Point2D getPosition() {

        return position;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setPosition(Point2D newPos) {
        this.position = newPos;
    }

    public void setDirection(Point2D newDir) {

        this.direction = newDir.normalize();
    }
}
