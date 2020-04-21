package View;

import Model.Ray;
import Model.RayTracer;
import Model.RayWorldContact;
import Model.World;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class RayTraceView {

    private final World world;

    public RayTraceView(World world) {
        this.world = world;
    }

    public void drawShapes(int left, int top, int width, int height, GraphicsContext gc) {


        gc.setStroke(Color.BLACK);
        gc.strokeRect(left, top, width,height);

        Camera camera = new Camera();
        camera.translate(left,top);
        camera.scale(Math.min(width, height));


        var pos = world.getObserver().getPosition();
        var observerWindowCenterPos = camera.toLevelView(pos);
        var scale = 0.1 * camera.getScale();
        gc.fillOval(observerWindowCenterPos.getX() - scale/2.0, observerWindowCenterPos.getY() - scale/2, scale, scale);



        RayTracer tracer = new RayTracer(world.getLevel());

        float fov = 90; //in degrees?
        int numRays = 100;
        for (int x = 0; x < numRays; x++) {
            Ray ray = tracer.getRay(world.getObserver(), fov, Math.sqrt(2), x, numRays);
            RayWorldContact contact = tracer.trace(ray);

            if (contact.hasContact() ) {
                Point2D contactModelPos = contact.getPosition();
                var contactWindowPos = camera.toLevelView(contactModelPos);
                gc.strokeLine(observerWindowCenterPos.getX(), observerWindowCenterPos.getY(), contactWindowPos.getX(), contactWindowPos.getY());
            }
        }
    }
}
