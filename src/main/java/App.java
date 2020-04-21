import Model.Level;
import Model.World;
import View.RayTraceView;
import javafx.application.Application;
import javafx.stage.Stage;
import View.JavaFXView;
import View.LevelView;


public class App  extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        var world = new World();
        var view = new JavaFXView(new LevelView(world), new RayTraceView(world));
        view.start(stage);
    }

    public static void main(String args[]) {
        launch(args);
    }



}
