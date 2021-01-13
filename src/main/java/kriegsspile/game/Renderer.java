package kriegsspile.game;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.entity.Units;

import java.util.List;

public class Renderer {
    private Map map;
    private Rectangle[][] screenField;
    private Group group;
    private EventHandler<MouseEvent> mouseHandler;
    private int enableI = -1;
    private int enableJ = -1;

    public Renderer(Group group, EventHandler<MouseEvent> mouseHandler) {
        map = new Map();
        screenField = new Rectangle[GlobalConstants.MAP_H][GlobalConstants.MAP_W];
        this.group = group;
        this.mouseHandler = mouseHandler;
    }

    public void init(List<Units> units) {
        map.setAsGameInfo(units);
        for (int i = 0; i < GlobalConstants.MAP_H; i++) {
            for (int j = 0; j < GlobalConstants.MAP_W; j++) {
                screenField[i][j] = new Rectangle(j * GlobalConstants.MAP_CELL_SIZE,
                        50+i * GlobalConstants.MAP_CELL_SIZE,
                        GlobalConstants.MAP_CELL_SIZE,
                        50+GlobalConstants.MAP_CELL_SIZE);
                screenField[i][j].setFill(Color.DARKGRAY);
                group.getChildren().addAll(screenField[i][j]);
                screenField[i][j].setOnMouseClicked(mouseHandler);
            }
        }
        paintMap();
    }

    public void update(List<Units> units) {
        map.setAsGameInfo(units);
    }

    public void enableRec(int i, int j) {
        enableI = i;
        enableJ = j;
    }

    public void paintMap() {
        for (int i = 0; i < GlobalConstants.MAP_H; i++) {
            for (int j = 0; j < GlobalConstants.MAP_W; j++) {
                switch (map.getCellState(i, j)) {
                    case NONE:
                        screenField[i][j].setFill(Color.DARKGRAY);
                        break;
                    case U_TANK:
                        screenField[i][j].setFill(Color.GREENYELLOW);
                        break;
                    case E_TANK:
                        screenField[i][j].setFill(Color.ORANGERED);
                        break;
                    case U_INFANTRY:
                        screenField[i][j].setFill(Color.DARKGREEN);
                        break;
                    case E_INFANTRY:
                        screenField[i][j].setFill(Color.DARKRED);
                        break;
                }
            }
        }
        if (enableI != -1 && enableJ != -1) {
            screenField[enableI][enableJ].setFill(Color.BLACK);
        }
    }

    public int getEnableI() {
        return enableI;
    }

    public void setEnableI(int enableI) {
        this.enableI = enableI;
    }

    public int getEnableJ() {
        return enableJ;
    }

    public void setEnableJ(int enableJ) {
        this.enableJ = enableJ;
    }
}
