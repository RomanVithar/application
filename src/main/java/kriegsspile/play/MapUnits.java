package kriegsspile.play;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.entity.Units;

@JsonAutoDetect
public class MapUnits {
    private Units[][] map = new Units[GlobalConstants.MAP_H][GlobalConstants.MAP_W];

    public Units getUnit(int i, int j) {
        if (i < 0 || j < 0 || i >= GlobalConstants.MAP_H || j >= GlobalConstants.MAP_W) {
            return null;
        }
        return map[i][j];
    }

    public void setUnit(int i, int j, Units units) {
        map[i][j] = units;
    }

    public Units[][] getMap() {
        return map;
    }

    public void remove(int i, int j) {
        map[i][j] = null;
    }
}
