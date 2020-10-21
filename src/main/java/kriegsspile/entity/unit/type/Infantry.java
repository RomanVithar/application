package kriegsspile.entity.unit.type;

import kriegsspile.entity.unit.UnitType;
import kriegsspile.entity.unit.Units;

public class Infantry extends Units {

    public Infantry(String playerName) {
        this.playerName = playerName;
        unitType = UnitType.INFANTRY;
        armor = 10;
        strong = 10;
        lengthMove = 1;
    }
}
