package kriegsspile.play;

import com.fasterxml.jackson.databind.ObjectMapper;
import kriegsspile.constants.GlobalConstants;
import kriegsspile.entity.Units;
import kriegsspile.entity.type.Infantry;
import kriegsspile.entity.type.Tank;
import kriegsspile.services.GameMath;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private MapUnits map;
    private List<Units> UUnits;
    private List<Units> EUnits;

    public GameManager() {
    }

    public void startGame() {
        map = new MapUnits();
        UUnits = new ArrayList<>();
        EUnits = new ArrayList<>();
        arrangeRegions();
        arrangeUnits(true);
        arrangeUnits(false);
    }

    public List<Units> move(int x1, int y1, int x2, int y2) {
        if (map.getUnit(y1, x1) == null) {
            System.out.println("В этой точке нет ваших отрядов");
            return getVision();
        }
        if (!map.getUnit(y1, x1).isUser()) {
            System.out.println("В этой точке нет ваших отрядов");
            return getVision();
        }
        if (Math.abs(x2 - x1) > map.getUnit(y1, x1).getLengthMove()
                || Math.abs(y2 - y1) > map.getUnit(y1, x1).getLengthMove()) {
            System.out.println("Данный отряд не может переместиться в эту точку");
            return getVision();
        }
        if (map.getUnit(y2, x2) != null) {
            if (!map.getUnit(y2, x2).isUser()) {
                removeFromListE(map.getUnit(y2,x2));
                Units unitOn1 = map.getUnit(y1, x1);
                map.remove(y1, x1);
                Units aliveUnit = GameMath.calculateWhoAlive(unitOn1, map.getUnit(y2, x2));
                aliveUnit.setPosition(new Point(x2, y2));
                if (aliveUnit.isUser()) {
                    removeFromListE(map.getUnit(y2, x2));
                    removeFromListU(unitOn1);
                    UUnits.add(aliveUnit);
                } else {
                    removeFromListU(unitOn1);
                    removeFromListE(map.getUnit(y2, x2));
                    EUnits.add(aliveUnit);
                }
                map.setUnit(y2, x2, aliveUnit);
                enemyStep();
                return getVision();
            } else {
                System.out.println("Пока не доступное перемещение");
                return getVision();
            }
        } else {
            if (y2 < GlobalConstants.MAP_H && y2 >= 0 && x2 < GlobalConstants.MAP_W && x2 >= 0) {
                map.setUnit(y2, x2, map.getUnit(y1, x1));
                map.setUnit(y1, x1, null);
                map.getUnit(y2, x2).setPosition(new Point(x2, y2));
                enemyStep();
                return getVision();
            } else {
                System.out.println("Выход за предеы карты");
                return getVision();
            }
        }
    }

    private void enemyStep() {
        Units units = EUnits.get((int) (Math.random() * EUnits.size()));
        int dx = (int) (1 - Math.random() * 2 + 1);
        int dy = (int) (1 - Math.random() * 2 + 1);
        if (units.getPosition().x + dx < GlobalConstants.MAP_W &&
                units.getPosition().y + dy < GlobalConstants.MAP_H &&
                units.getPosition().x + dx > -1 &&
                units.getPosition().y + dy > -1) {
            if (map.getUnit(units.getPosition().y + dy, units.getPosition().x + dx) == null) {
                map.remove(units.getPosition().y, units.getPosition().x);
                units.setPosition(new Point(units.getPosition().x + dx, units.getPosition().y + dy));
                map.setUnit(units.getPosition().y + dy, units.getPosition().x + dx, units);
            }
        }
    }

    public List<Units> getVision() {
        List<Units> unitsForPlayer = new ArrayList<>();
        for (Units uUnit : UUnits) {
            unitsForPlayer.add(uUnit);
            for (int i = 0; i < EUnits.size(); i++) {
                if (uUnit.nearby(EUnits.get(i))) {
                    if (!unitsForPlayer.contains(EUnits.get(i))) {
                        unitsForPlayer.add(EUnits.get(i));
                    }
                }
            }
        }
        GameMath.determineInfReceived(unitsForPlayer);
        return unitsForPlayer;
    }

    void removeFromListU(Units unit) {
        for (int i = 0; i < UUnits.size(); i++) {
            if (UUnits.get(i).getPosition().x == unit.getPosition().x &&
                    UUnits.get(i).getPosition().y == unit.getPosition().y) {
                UUnits.remove(i);
                return;
            }
        }
    }

    void removeFromListE(Units unit) {
        for (int i = 0; i < EUnits.size(); i++) {
            if (UUnits.get(i).getPosition().x == unit.getPosition().x &&
                    UUnits.get(i).getPosition().y == unit.getPosition().y) {
                EUnits.remove(i);
                return;
            }
        }
    }

    private void arrangeUnits(Boolean isPlayer) {
        //заполняем карту и списки пехотой
        for (int i = 0; i < GlobalConstants.NUMBER_INFANTRY; i++) {
            Units units = new Infantry(isPlayer);
            fillMapUnitsBy(GlobalConstants.QUANTITY_INFANTRY, units);
            if (isPlayer) {
                UUnits.add(units);
            } else {
                EUnits.add(units);
            }
        }
        //заполняем карту и списки танками
        for (int i = 0; i < GlobalConstants.NUMBER_TANK; i++) {
            Units units = new Tank(isPlayer);
            fillMapUnitsBy(GlobalConstants.QUANTITY_TANK, units);
            UUnits.add(units);
            if (isPlayer) {
                UUnits.add(units);
            } else {
                EUnits.add(units);
            }
        }
    }

    private void fillMapUnitsBy(int quantity, Units units) {
        int x = (int) (Math.random() * GlobalConstants.MAP_H);
        int y = (int) (Math.random() * GlobalConstants.MAP_W);
        while (map.getUnit(y, x) != null) {
            x = (int) (Math.random() * GlobalConstants.MAP_H);
            y = (int) (Math.random() * GlobalConstants.MAP_W);
        }
        map.setUnit(y, x, units);
        units.setPosition(new Point(x, y));
        units.setQuantity(quantity);
    }

    private void arrangeRegions() {
          /*
        TODO расставить регионы
         */
    }

    public void serialize() throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, map);
        String result = writer.toString();
        try(FileWriter writerFile = new FileWriter("serializeData.txt", false))
        {
            writerFile.write(result);
            writerFile.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Сериализовали");
    }

    public void deserialize() throws IOException {
        String jsonString;
        BufferedReader reader2 = new BufferedReader(new FileReader("serializeData.txt"));
        jsonString = reader2.readLine();
        StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(reader, MapUnits.class);
        UUnits = new ArrayList<>();
        EUnits = new ArrayList<>();
        for (int i = 0; i < GlobalConstants.MAP_H; i++) {
            for (int j = 0; j < GlobalConstants.MAP_W; j++) {
                if(map.getUnit(i,j) != null){
                    if(map.getUnit(i,j).isUser()){
                        UUnits.add(map.getUnit(i,j));
                    }else{
                        EUnits.add(map.getUnit(i,j));
                    }
                }
            }
        }
        System.out.println("Десериализовали");
    }
}
