package pt.up.fe.ldts.controller.employeeAI;

import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Jorge;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;

public class AITest {

    Point pos;

    @BeforeEach
    public void setup(){
        pos = new Point(5,6);
        Jorge.singleton.setDirection(Vector.UP);
        Jorge.singleton.changePos(4, 5);
    }

    @Test
    public void toniTest(){
        EmployeeAI toni = new ToniAI();

        Assertions.assertEquals(toni.getScatterTarget(), toni.chooseTargetPosition(Employee.EmployeeState.SCATTER, pos));

        Assertions.assertEquals(toni.getScatterTarget(), toni.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

        pos = pos.addVector(new Vector(10,10));

        Assertions.assertEquals(Jorge.singleton.getPosition(), toni.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

        Assertions.assertEquals(ToniAI.DEAD_TARGET, toni.chooseTargetPosition(Employee.EmployeeState.DEAD, pos));

        //falta testar frightened

        TextColor expectedColor = TextColor.Factory.fromString("#e5b362");

        Assertions.assertEquals(expectedColor, toni.getEmployeeColor());
    }

    @Test
    public void baltaTest(){
        EmployeeAI baltazar = new BaltaAI();

        Assertions.assertEquals(baltazar.getScatterTarget(), baltazar.chooseTargetPosition(Employee.EmployeeState.SCATTER, pos));

        Assertions.assertEquals(Jorge.singleton.getPosition(), baltazar.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

        Assertions.assertEquals(BaltaAI.DEAD_TARGET, baltazar.chooseTargetPosition(Employee.EmployeeState.DEAD, pos));

        TextColor expectedColor = TextColor.Factory.fromString("#E70000");

        Assertions.assertEquals(expectedColor, baltazar.getEmployeeColor());
    }

    @Test
    public void mariTest(){
        EmployeeAI mariana = new MariAI();
        Point target = Jorge.singleton.getPosition().addVector(new Vector(-4,-4));

        Assertions.assertEquals(mariana.getScatterTarget(), mariana.chooseTargetPosition(Employee.EmployeeState.SCATTER, pos));

        Assertions.assertEquals(target, mariana.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

        Jorge.singleton.setDirection(Vector.LEFT);

        target = Jorge.singleton.getPosition().addVector(Vector.LEFT.multiply(4));

        Assertions.assertEquals(target, mariana.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

        Assertions.assertEquals(MariAI.DEAD_TARGET, mariana.chooseTargetPosition(Employee.EmployeeState.DEAD, pos));

        TextColor expectedColor = TextColor.Factory.fromString("#ed8fe4");

        Assertions.assertEquals(expectedColor, mariana.getEmployeeColor());
    }

    @Test
    public void zeCastroTest(){

        Employee balta = new Employee(10,10,null);

        EmployeeAI zeCastro = new ZeCastroAI(balta);

        Point target;

        {
            Assertions.assertEquals(Vector.UP, Jorge.singleton.getDirection());

            target = Jorge.singleton.getPosition().addVector(new Vector(-2, -2));

            target = target.addVector(Vector.from(balta.getPosition(), target));

            Assertions.assertEquals(zeCastro.getScatterTarget(), zeCastro.chooseTargetPosition(Employee.EmployeeState.SCATTER, pos));

            Assertions.assertEquals(target, zeCastro.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));
        }

        Jorge.singleton.setDirection(Vector.LEFT);

        {
            target = Jorge.singleton.getPosition().addVector(Vector.LEFT.multiply(2));

            target = target.addVector(Vector.from(balta.getPosition(), target));

            Assertions.assertEquals(target, zeCastro.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

            Assertions.assertEquals(ZeCastroAI.DEAD_TARGET, zeCastro.chooseTargetPosition(Employee.EmployeeState.DEAD, pos));
        }

        Jorge.singleton.setDirection(Vector.UP);
        balta.changePos(4, 3);

        {
            target = Jorge.singleton.getPosition().addVector(new Vector(-2, -2));

            target = target.addVector(Vector.from(balta.getPosition(), target));

            Assertions.assertEquals(target, zeCastro.chooseTargetPosition(Employee.EmployeeState.CHASING, pos));

            Assertions.assertEquals(ZeCastroAI.DEAD_TARGET, zeCastro.chooseTargetPosition(Employee.EmployeeState.DEAD, pos));
        }

        TextColor expectedColor = TextColor.Factory.fromString("#43eee4");

        Assertions.assertEquals(expectedColor, zeCastro.getEmployeeColor());
    }
}
