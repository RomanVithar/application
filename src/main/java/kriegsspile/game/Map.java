package kriegsspile.game;

import javafx.scene.paint.Color;
import kriegsspile.constants.CellState;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.dto.GameInformation;
import kriegsspile.entity.Units;

public class Map {
    private CellState[][] map = new CellState[GlobalConstants.MAP_H][GlobalConstants.MAP_W];

    public Map() {
        fillMap(map, CellState.NONE);
    }


    private void fillMap(CellState[][] map, CellState cellState) {
        for (int i = 0; i < GlobalConstants.MAP_H; i++) {
            for (int j = 0; j < GlobalConstants.MAP_W; j++) {
                map[i][j] = cellState;
            }
        }
    }

    public void setAsGameInfo(GameInformation gameInfo) {
        for (Units units : gameInfo.myUnits) {
            switch (units.getUnitType()) {
                case TANK:
                    map[units.getPosition().y][units.getPosition().x] = CellState.U_TANK;
                    break;
                case INFANTRY:
                    map[units.getPosition().y][units.getPosition().x] = CellState.U_INFANTRY;
                    break;
            }
        }
        for (Units units : gameInfo.myUnits) {
            switch (units.getUnitType()) {
                case TANK:
                    map[units.getPosition().y][units.getPosition().x] = CellState.E_TANK;
                    break;
                case INFANTRY:
                    map[units.getPosition().y][units.getPosition().x] = CellState.E_INFANTRY;
                    break;
            }
        }
        /*
        todo скорее всего надо будет дописать
         */
    }

    public void setCellState(int i, int j, CellState cellState) {
        map[i][j] = cellState;
    }

    public CellState getCellState(int i, int j) {
        return map[i][j];
    }
}
