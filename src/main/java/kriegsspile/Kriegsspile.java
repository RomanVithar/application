package kriegsspile;

import javafx.application.Application;
import javafx.stage.Stage;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.scene.main_scene.MainScene;


public class Kriegsspile extends Application {
    MainScene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception {

        mainScene = new MainScene();
        primaryStage.setScene(mainScene.getScene());
        primaryStage.setTitle(GlobalConstants.GAME_NAME + GlobalConstants.GAME_VERSION);
        primaryStage.show();
    }
}