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

        var fov = Math.PI/2.0; //in degrees?
        int numRays = 200;
        for (int x = 0; x < numRays; x++) {
            Ray ray = tracer.getRay(world.getObserver().getPosition(), world.getObserver().getDirection(), fov, Math.sqrt(2.0), x, numRays);
            RayWorldContact contact = tracer.traceClosest(ray, null);

            if (contact.hasContact() ) {
                double lightIntensity = tracer.getIntensity(contact, 0);
                lightIntensity = Math.min(lightIntensity, 1.0);
                Color col = Color.color(lightIntensity, lightIntensity, lightIntensity);
                gc.setStroke(col);
                Point2D contactModelPos = contact.getPosition();
                var contactWindowPos = camera.toLevelView(contactModelPos);
                gc.strokeLine(observerWindowCenterPos.getX(), observerWindowCenterPos.getY(), contactWindowPos.getX(), contactWindowPos.getY());
                gc.strokeLine(200 + (200-x), 0, 200 + (200-x), 200);

            } else {
                gc.setStroke(Color.BLACK);
                Point2D rayEndPos = ray.getEnd();
                var endWindowPos = camera.toLevelView(rayEndPos);
                gc.strokeLine(observerWindowCenterPos.getX(), observerWindowCenterPos.getY(), endWindowPos.getX(), endWindowPos.getY());
                gc.strokeLine(200 + x, 0, 200 + x, 200);
            }
        }
    }
}
