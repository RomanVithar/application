package kriegsspile.game;

import kriegsspile.constants.CellState;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.dto.GameInformation;
import sun.management.counter.Units;

import java.util.List;

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
        /*
        todo заполнить из gameInfo
         */
    }

    public void setCellState(int i, int j, CellState cellState) {
        map[i][j] = cellState;
    }

    public CellState getCellState(int i, int j) {
        return map[i][j];
    }
}
