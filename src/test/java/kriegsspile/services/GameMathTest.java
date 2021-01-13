package kriegsspile.services;

import kriegsspile.entity.Units;
import kriegsspile.entity.type.Infantry;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMathTest {

    @Test
    void determineInfReceived() {
        List<Units> units = new ArrayList<>();
        units.add(new Units());
        units.add(new Units());
        int x  = units.size();
        GameMath.determineInfReceived(units);
        assertEquals(x, units.size());
    }

    @Test
    void calculateWhoAlive() {
        Units units1 = new Infantry(true);
        Units units2 = new Infantry(true);
        units1.setQuantity(10000);
        GameMath.calculateWhoAlive(units1, units2);
        assertEquals(units1, GameMath.calculateWhoAlive(units1, units2));
    }
}