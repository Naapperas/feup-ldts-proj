package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.game.Jorge;
import pt.up.fe.ldts.model.map.MapConfiguration;

public class BaltaAI extends EmployeeAI{ //blinky

    @Override
    public Point getScatterTarget() {
        return new Point(MapConfiguration.getMapWidth()-1,0);
    }

    @Override
    public Point chasingTarget(Point position) {
        return Jorge.singleton.getPosition();
    }

    @Override
    public TextColor getEmployeeColor() {
        return TextColor.Factory.fromString("#E70000");
    }
}
