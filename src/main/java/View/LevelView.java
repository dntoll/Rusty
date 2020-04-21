package View;

import Model.Level;
import Model.Line;
import Model.Observer;
import Model.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LevelView {

    private final World world;
    private Camera camera;

    public LevelView(World w) {
        this.world = w;
        camera = new Camera();
    }

    public void drawShapes(double left, double top, double width, double height, GraphicsContext gc ) {
        camera = new Camera();
        camera.translate(left,top);
        camera.scale(Math.min(width, height));

        gc.setStroke(Color.BLACK);
        gc.strokeRect(left, top, width,height);

        drawLevel(gc, world.getLevel());
        drawObserver(gc, world.getObserver());

    }

    private void drawObserver(GraphicsContext gc, Observer observer) {
        gc.setFill(Color.BLUE);

        var pos = observer.getPosition();
        var windowCenterPos = camera.toLevelView(pos);
        var scale = 0.1 * camera.getScale();
        gc.fillOval(windowCenterPos.getX() - scale/2.0, windowCenterPos.getY() - scale/2, scale, scale);


        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);

        var dir = observer.getDirection();
        var endpoint = pos.add(dir.multiply(scale));

        var directionWindowsPosition = camera.toLevelView(endpoint);

        gc.strokeLine(windowCenterPos.getX(), windowCenterPos.getY(), directionWindowsPosition.getX(), directionWindowsPosition.getY());
    }

    private void drawLevel(GraphicsContext gc, Level level) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);

        for (Line l : level.getLines())
        {

            var windowStartPos = camera.toLevelView(l.start);
            var windowEndPos = camera.toLevelView(l.end);
            gc.strokeLine(windowStartPos.getX(), windowStartPos.getY(), windowEndPos.getX(), windowEndPos.getY());
        }
    }


}
