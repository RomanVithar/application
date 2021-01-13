package kriegsspile.scene.main_scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.game.Renderer;
import kriegsspile.play.GameManager;

import java.io.IOException;

public class MainScene {
    private Scene mainScene;
    private Group root;
    private FlowPane waitingRoomPane;
    private GameManager gameManager;
    private Renderer renderer;
    private boolean isSecondClick;

    public MainScene() throws IOException {
        root = new Group();
        mainScene = new Scene(root, GlobalConstants.SCENE_WIDTH, GlobalConstants.SCENE_HEIGHT+50, Color.LIGHTGRAY);
        gameManager = new GameManager();
        waitingRoomGroupInit();
    }

    private void waitingRoomGroupInit() {
        Button btnStart = new Button("Начать игру");
        btnStart.requestFocus();
        Button btnContinue = new Button("Продолжить игру");
        waitingRoomPane = new FlowPane(Orientation.VERTICAL, 70, 70, btnStart, btnContinue);
        waitingRoomPane.setAlignment(Pos.CENTER);
        root.getChildren().addAll(waitingRoomPane);
        isSecondClick = false;

        EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(isSecondClick){
                    renderer.update(gameManager.move(renderer.getEnableJ(), renderer.getEnableI(),
                            (int) (mouseEvent.getX()) / GlobalConstants.MAP_CELL_SIZE,
                            (int) (mouseEvent.getY()-50) / GlobalConstants.MAP_CELL_SIZE));
                    isSecondClick = !isSecondClick;
                }else {
                    renderer.enableRec((int) (mouseEvent.getY()-50) / GlobalConstants.MAP_CELL_SIZE,
                            (int) (mouseEvent.getX()) / GlobalConstants.MAP_CELL_SIZE);
                    isSecondClick = !isSecondClick;
                }
                renderer.paintMap();
            }
        };
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().removeAll(waitingRoomPane);
                renderer = new Renderer(root, mouseHandler);
                gameManager.startGame();
                renderer.init(gameManager.getVision());
                Button buttonStop = new Button("Стоп");
                root.getChildren().addAll(buttonStop);
                buttonStop.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            gameManager.serialize();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                });
            }
        });
        btnContinue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().removeAll(waitingRoomPane);
                renderer = new Renderer(root, mouseHandler);
                try {
                    gameManager.deserialize();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                renderer.init(gameManager.getVision());
                Button buttonStop = new Button("Стоп");
                root.getChildren().addAll(buttonStop);
                buttonStop.setOnAction( new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            gameManager.serialize();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                    }
                });
            }
        });

    }


    public Scene getScene() {
        return mainScene;
    }
}
