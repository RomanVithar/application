package kriegsspile.scene.main_scene;

import com.sun.scenario.animation.shared.TimelineClipCore;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.dto.GameInformation;
import kriegsspile.game.Renderer;
import kriegsspile.requests.Requester;

import java.io.IOException;

public class MainScene {
    private Scene mainScene;
    private Group root;
    private FlowPane authPane;
    private FlowPane waitingRoomPane;
    private FlowPane gamePane;
    private String playerName;

    public MainScene() {
        root = new Group();
        mainScene = new Scene(root, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT, Color.LIGHTGRAY);
        authGroupInit();
    }

    private void authGroupInit() {
        Label lblStartString = new Label(GlobalConstants.GAME_NAME);
        lblStartString.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        TextField tfStart = new TextField();
        Button btnSend = new Button("Ввести имя");
        tfStart.setPromptText("Введите имя в игре");
        Label state = new Label("");
        authPane = new FlowPane(Orientation.VERTICAL, 70, 70, lblStartString, tfStart, state, btnSend);
        authPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(authPane);

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tfStart.getText().equals("")) {
                    state.setText("Это поле не может быть пустым");
                    return;
                }
                GameInformation gInfo;
                try {
                    gInfo = Requester.addPlayer(tfStart.getText());
                } catch (IOException e) {
                    state.setText("Нет подключения к серверу");
                    e.printStackTrace();
                    return;
                }
                if (gInfo.messageResponse != null) {
                    state.setText(gInfo.messageResponse);
                    return;
                }
                playerName = tfStart.getText();
                state.setText("Ваше имя успешно отправлено");
                root.getChildren().removeAll(authPane);
                waitingRoomGroupInit();
            }
        });

    }

    private void waitingRoomGroupInit() {
        Button btnStart = new Button("Начать игру");
        btnStart.requestFocus();
        waitingRoomPane = new FlowPane(Orientation.VERTICAL, 70, 70, btnStart);
        waitingRoomPane.setAlignment(Pos.CENTER);
        authPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(waitingRoomPane);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Requester.startGame();
                    root.getChildren().removeAll(waitingRoomPane);
                    gamePaneInit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void gamePaneInit() throws IOException {
        Renderer renderer = new Renderer(root);
        renderer.init(Requester.getVision(playerName));
        /*
        todo обновление после хода
         */
    }

    public Scene getScene() {
        return mainScene;
    }
}
