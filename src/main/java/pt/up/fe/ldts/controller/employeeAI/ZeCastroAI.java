package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.game.Employee;
import pt.up.fe.ldts.model.game.Jorge;
import pt.up.fe.ldts.model.map.MapConfiguration;

public class ZeCastroAI extends EmployeeAI{ //inky

    private final Employee balta;

    public ZeCastroAI(Employee balta){
        this.balta = balta;
    }

    @Override
    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#43eee4");
    }

    @Override
    public Point getScatterTarget() {
        return new Point(MapConfiguration.getMapWidth()-1, MapConfiguration.getMapHeight()-1);
    }

    /**
     * ZeCastro's targeted position when chasing Jorge
     * @return targeted position when chasing Jorge
     */
    @Override
    public Point chasingTarget(Point position){
        Point target;
        if(Jorge.singleton.getDirection().equals(Vector.UP))
            target = Jorge.singleton.getPosition().addVector(Vector.UP.multiply(2)).addVector(Vector.LEFT.multiply(2));
        else
            target = Jorge.singleton.getPosition().addVector(Jorge.singleton.getDirection().multiply(2));

        return target.addVector(Vector.from(balta.getPosition(), target));
    }
}
