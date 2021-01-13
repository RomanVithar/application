package kriegsspile.game;

import kriegsspile.constants.CellState;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.entity.Units;

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

    public void setAsGameInfo(List<Units> units) {
        CellState cs;
        fillMap(map, CellState.NONE);
        for (Units unit : units) {
            switch (unit.getUnitType()) {
                case TANK:
                    cs = CellState.E_TANK;
                    if(unit.isUser()){
                        cs = CellState.U_TANK;
                    }
                    map[unit.getPosition().y][unit.getPosition().x] = cs;
                    break;
                case INFANTRY:
                    cs = CellState.E_INFANTRY;
                    if(unit.isUser()){
                        cs = CellState.U_INFANTRY;
                    }
                    map[unit.getPosition().y][unit.getPosition().x] = cs;
                    break;
            }
        }
    }

    public void setCellState(int i, int j, CellState cellState) {
        map[i][j] = cellState;
    }

    public CellState getCellState(int i, int j) {
        return map[i][j];
    }

    public boolean isThatCellNone(int i, int j) {
        if(map[i][j] == CellState.NONE) {
            return true;
        }
        return false;
    }
}
