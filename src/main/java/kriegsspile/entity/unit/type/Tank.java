package kriegsspile.entity.unit.type;


import kriegsspile.entity.unit.UnitType;
import kriegsspile.entity.unit.Units;

public class Tank extends Units {

    public Tank(String playerName) {
        this.playerName = playerName;
        unitType = UnitType.TANK;
        strong = 20;
        armor = 40;
        lengthMove = 2;
    }
}
