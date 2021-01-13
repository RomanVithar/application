package kriegsspile.entity;

import kriegsspile.entity.type.Infantry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class UnitsTest {

    @Test
    void nearby() {
        Units units1 = new Infantry(true);
        Units units2 = new Infantry(true);
        units1.setPosition(new Point(0,0));
        units2.setPosition(new Point(0,1));
        assertTrue(units1.nearby(units2));
    }
}