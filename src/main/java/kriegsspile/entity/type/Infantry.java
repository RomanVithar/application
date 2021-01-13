package kriegsspile.entity.type;

import kriegsspile.entity.UnitType;
import kriegsspile.entity.Units;

public class Infantry extends Units {

    public Infantry(Boolean isUser) {
        this.isUser = isUser;
        unitType = UnitType.INFANTRY;
        armor = 10;
        strong = 10;
        lengthMove = 1;
        if(isUser){
            quantity = 200;
        }else{
            quantity = 10;
        }
    }
}
