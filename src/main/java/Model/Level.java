package Model;


import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Level {
    private ArrayList<Line> lines = new ArrayList<Line>();

    public Level() {

    }

    public void addLine(Line toBeAdded) {
        lines.add(toBeAdded);
    }

    public Line[] getLines() {
        return (Line[]) this.lines.toArray(new Line[0]);
    }
}
