package kriegsspile.scene;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.dto.GameInformation;
import kriegsspile.requests.Requester;

import java.io.IOException;
import java.util.Set;

public class MainScene {
    private Scene mainScene;
    private FlowPane pane;

    Label lblStartString;
    TextField tfStart;
    Button btnSend;
    Label state;
    Button btnStart;
    Set<String> names;

    public void init() {
        initPane();

        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tfStart.getText().equals("")) {
                    state.setText("Это поле не может быть пустым");
                    return;
                }
                GameInformation gInfo = new GameInformation();
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
                state.setText("Ваше имя успешно отправлено");
                pane.getChildren().removeAll(tfStart, btnSend);
                pane.getChildren().add(btnStart);
            }
        });

        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Requester.startGame();
                    pane.getChildren().removeAll(btnStart, state, lblStartString);

                    pane.getChildren().addAll();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initPane() {
        lblStartString = new Label(GlobalConstants.GAME_NAME);
        lblStartString.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        state = new Label("");
        btnSend = new Button("Ввести имя");
        tfStart = new TextField();
        tfStart.setPromptText("Введите имя в игре");
        btnStart = new Button("Начать игру");
        btnStart.requestFocus();
        pane = new FlowPane(Orientation.VERTICAL, 70, 70, lblStartString, tfStart, state, btnSend);
        pane.setAlignment(Pos.CENTER);
        mainScene = new Scene(pane, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT);
    }

    public Scene getScene() {
        return mainScene;
    }
}
