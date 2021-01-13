package kriegsspile.services;

import kriegsspile.entity.Units;

import java.util.List;

public class GameMath {
    private static final double chanceOnDeliveryInf = 1;
    /*
    todo на время разработки шанс поставил 100 процентов потом это исправить
     */
    public static void determineInfReceived(List<Units> units) {
        for (int i = 0; i < units.size(); i++) {
            if (Math.random() > chanceOnDeliveryInf) {
                units.remove(0);
            }
        }
    }

    public static Units calculateWhoAlive(Units unit1, Units unit2){
        if(unit1.getQuantity()*unit1.getStrong()>=unit2.getQuantity()*unit2.getStrong()){
            unit1.setQuantity((int)(unit1.getQuantity() - unit1.getQuantity()*0.01d*(unit2.getStrong()*10d/unit1.getArmor())));
            return unit1;
        }else{
            unit2.setQuantity((int)(unit2.getQuantity() - unit2.getQuantity()*0.01d*(unit1.getStrong()*10d/unit2.getArmor())));
            return unit2;
        }
    }
}