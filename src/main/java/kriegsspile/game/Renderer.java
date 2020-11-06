package kriegsspile.game;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.dto.GameInformation;

public class Renderer {
    private Map map;
    private Rectangle[][] screenField;
    private Group group;

    public Renderer(Group group) {
        map = new Map();
        screenField = new Rectangle[GlobalConstants.MAP_H][GlobalConstants.MAP_W];
        this.group = group;
    }

    public void init(GameInformation vision){
        map.setAsGameInfo(vision);
        for(int i=0;i<GlobalConstants.MAP_H;i++){
            for(int j=0;j<GlobalConstants.MAP_W;j++){
                screenField[i][j] = new Rectangle(j*GlobalConstants.MAP_CELL_SIZE,
                        i*GlobalConstants.MAP_CELL_SIZE,
                        GlobalConstants.MAP_CELL_SIZE,
                        GlobalConstants.MAP_CELL_SIZE);
                screenField[i][j].setFill(Color.DARKGRAY);
                group.getChildren().addAll(screenField[i][j]);
            }
        }
    }

    public void update(GameInformation vision){
        /*
        todo что то типа обработчика событий, послы выполнения события проверяется whoISTurn выполняется ход
        todo добавть таймер
         */
    }
}