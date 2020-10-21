package kriegsspile;

import kriegsspile.dto.GameInformation;
import kriegsspile.entity.unit.Units;
import kriegsspile.requests.Requester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Emulation {
    final String name1 = "gregfs";
    final String name2 = "grsthytghd";
    public void start() throws IOException {
        Requester.addPlayer(name2);
        Requester.addPlayer(name1);
        Requester.startGame();
        GameInformation qwe;
        qwe = Requester.getVision(name1);

        while(Requester.getVision(name1).myUnits.size()!=0 && Requester.getVision(name2).myUnits.size()!=0){
            List<Units> myUnits = Requester.getVision(name1).myUnits;
            GameInformation gi = new GameInformation();
            gi.messageResponse = "gfg";
            while (gi.messageResponse != null) {
                int x = myUnits.get((int) (Math.random() * myUnits.size() - 1)).getPosition().x;
                int y = myUnits.get((int) (Math.random() * myUnits.size() - 1)).getPosition().y;
                gi = Requester.move(name1, x, y,
                        x+1-(int) (Math.random() * 2),
                        y+1-(int) (Math.random() * 2));
            }
            myUnits = Requester.getVision(name2).myUnits;
            gi.messageResponse = "gfg";
            while (gi.messageResponse != null) {
                int x = myUnits.get((int) (Math.random() * myUnits.size() - 1)).getPosition().x;
                int y = myUnits.get((int) (Math.random() * myUnits.size() - 1)).getPosition().y;
                gi = Requester.move(name2, x, y,
                        x+1-(int) (Math.random() * 2),
                        y+1-(int) (Math.random() * 2));
            }
        }
    }
}
