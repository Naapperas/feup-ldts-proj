package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.game.Jorge;

public class MariAI extends EmployeeAI { //pinky

    @Override
    public Point getScatterTarget() {
        return new Point(0,0);
    };

    @Override
    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#ed8fe4");
    }

    /**
     * Mariana's targeted position when chasing Jorge
     * @return targeted position when chasing Jorge
     */
    @Override
    public Point chasingTarget(Point position){
        if(Jorge.singleton.getDirection().equals(Vector.UP))
            return Jorge.singleton.getPosition().addVector(Vector.UP.multiply(4)).addVector(Vector.LEFT.multiply(4));
        else
            return Jorge.singleton.getPosition().addVector(Jorge.singleton.getDirection().multiply(4));
    }
}
