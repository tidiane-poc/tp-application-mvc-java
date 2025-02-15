package ma.enset.iidbcc.tpapplicationmvc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.enset.iidbcc.tpapplicationmvc.state.AppGlobalState;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 640);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Medical Care Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        initializingData();
        launch();
    }

    private static void initializingData() {
        AppGlobalState.init();
    }
}