package kriegsspile;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.scene.MainScene;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

//
//public class Kriegsspile extends Application {
//    MainScene mainScene;
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        mainScene = new MainScene();
//        mainScene.init();
//        primaryStage.setScene(mainScene.getScene());
//        primaryStage.setTitle(GlobalConstants.GAME_NAME+GlobalConstants.GAME_VERSION);
//        primaryStage.show();
//    }
//}