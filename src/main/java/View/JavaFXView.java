package View;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class JavaFXView   {

    private final LevelView map;
    private final RayTraceView ray;
    private Camera camera;

    public JavaFXView(LevelView view, RayTraceView rayTraceView) {
        this.map = view;
        this.ray = rayTraceView;

    }

    public void start(Stage stage) throws Exception {


        Group root = new Group();
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        map.drawShapes(0,0, 200, 200, gc);
        ray.drawShapes(200,200, 200, 200, gc);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
