package kriegsspile.entity.type;


import kriegsspile.entity.UnitType;
import kriegsspile.entity.Units;

public class Tank extends Units {

    public Tank(Boolean isUser) {
        this.isUser = isUser;
        unitType = UnitType.TANK;
        strong = 20;
        armor = 40;
        lengthMove = 2;
        if(isUser){
            quantity = 200;
        }else{
            quantity = 10;
        }
    }
}
