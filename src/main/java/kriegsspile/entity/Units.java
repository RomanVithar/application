package kriegsspile.entity;

import java.awt.*;

public class Units {
    protected boolean isUser;
    protected UnitType unitType;
    protected Point position;
    protected int quantity;
    protected double strong;
    protected double armor;
    protected double lengthMove;


    public double getLengthMove() {
        return lengthMove;
    }

    public Point getPosition() {
        return position;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getStrong() {
        return strong;
    }

    public double getArmor() {
        return armor;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public boolean nearby(Units units){
        if(Math.abs(position.x - units.getPosition().x) <= lengthMove
                && Math.abs(position.y - units.getPosition().y) <= lengthMove){
            return true;
        }
        return false;
    }
}
