package kriegsspile.dto;

import kriegsspile.entity.unit.Units;

import java.util.ArrayList;
import java.util.List;

public class GameInformation {
    public List<Units> myUnits;
    public List<Units> enemyUnits;
    public String messageResponse;

    public GameInformation() {
        enemyUnits = new ArrayList<Units>();
        myUnits = new ArrayList<Units>();
    }

    public void setMessageResponse(String messageResponse) {
        this.messageResponse = messageResponse;
    }
}