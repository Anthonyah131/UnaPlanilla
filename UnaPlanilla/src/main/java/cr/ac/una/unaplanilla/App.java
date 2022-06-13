package cr.ac.una.unaplanilla;

import cr.ac.una.unaplanilla.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image("cr/ac/una/unaplanilla/resources/Usuario-48.png"));
        stage.setTitle("UNA Planilla");
        FlowController.getInstance().goViewInWindow("LoginView");
    }

    public static void main(String[] args) {
        launch();
    }

}