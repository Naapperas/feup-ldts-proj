package pt.up.fe.ldts.model.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;
import pt.up.fe.ldts.controller.employeeAI.ToniAI;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.game.Arena;
import pt.up.fe.ldts.model.game.Employee;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.*;

public class EmployeeTest {

    @Test
    public void testConstruction() {

        Employee employee = new Employee(4, 5, null);

        Assertions.assertEquals(Employee.EmployeeState.SCATTER, employee.getCurrentState());
    }

    private List<pt.up.fe.ldts.model.Vector> possibleDirections(pt.up.fe.ldts.model.Vector direction){
        List<pt.up.fe.ldts.model.Vector> directions = new ArrayList<>();

        if (pt.up.fe.ldts.model.Vector.UP.equals(direction)) {
            directions.add(pt.up.fe.ldts.model.Vector.UP);
            directions.add(pt.up.fe.ldts.model.Vector.LEFT);
            directions.add(pt.up.fe.ldts.model.Vector.RIGHT);
        } else if (pt.up.fe.ldts.model.Vector.LEFT.equals(direction)) {
            directions.add(pt.up.fe.ldts.model.Vector.LEFT);
            directions.add(pt.up.fe.ldts.model.Vector.DOWN);
            directions.add(pt.up.fe.ldts.model.Vector.UP);
        } else if (pt.up.fe.ldts.model.Vector.DOWN.equals(direction)) {
            directions.add(pt.up.fe.ldts.model.Vector.DOWN);
            directions.add(pt.up.fe.ldts.model.Vector.RIGHT);
            directions.add(pt.up.fe.ldts.model.Vector.LEFT);
        } else if (pt.up.fe.ldts.model.Vector.RIGHT.equals(direction)) {
            directions.add(pt.up.fe.ldts.model.Vector.RIGHT);
            directions.add(pt.up.fe.ldts.model.Vector.UP);
            directions.add(pt.up.fe.ldts.model.Vector.DOWN);
        }
        return directions;
    }

    @Test
    public void testInsideBox(){
        Employee employee = new Employee(13, 13, new ToniAI()); // ai doesn't matter here
        employee.setDirection(pt.up.fe.ldts.model.Vector.DOWN);

        Set<pt.up.fe.ldts.model.Vector> everyDir = new HashSet<>();
        everyDir.add(pt.up.fe.ldts.model.Vector.UP);
        everyDir.add(pt.up.fe.ldts.model.Vector.DOWN);
        everyDir.add(pt.up.fe.ldts.model.Vector.LEFT);
        everyDir.add(pt.up.fe.ldts.model.Vector.RIGHT);
        Arena arena = Mockito.mock(Arena.class);
        Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(everyDir);
        Mockito.when(arena.isInsideBox(Mockito.any())).thenReturn(true);
        Mockito.when(arena.getGatePosition()).thenReturn(new Point(13, 12));
        employee.changeDirection(arena);

        Assertions.assertEquals(pt.up.fe.ldts.model.Vector.UP, employee.getDirection());
    }

    @Test
    public void testCervejaPicked() throws InterruptedException {
        var previousTIME_FRIGHTNED = Employee.TIME_FRIGHTENED;
        Employee.TIME_FRIGHTENED = 1;

        Employee employee = new Employee(13, 13, new ToniAI()); // ai doesn't matter here
        var previousState = employee.getCurrentState();
        var previousDirection = employee.getDirection();

        employee.cervejaPicked();

        Assertions.assertEquals(previousDirection.multiply(-1), employee.getDirection());
        Assertions.assertEquals(Employee.EmployeeState.FRIGHTENED, employee.getCurrentState());

        Thread.sleep((Employee.TIME_FRIGHTENED+2) * 1000);

        Assertions.assertEquals(previousState, employee.getCurrentState());
        Assertions.assertEquals(previousDirection, employee.getDirection());

        Employee.TIME_FRIGHTENED = previousTIME_FRIGHTNED;
    }

    @Test
    public void testStates() {

        Employee employee = new Employee(4, 5, null);
        employee.setCurrentState(Employee.EmployeeState.DEAD);

        Assertions.assertEquals(Employee.EmployeeState.DEAD, employee.getCurrentState());
    }

    @Test
    public void testNotifiable() {

        Point basePoint = new Point(4, 5);

        Employee employee;

        EmployeeAI testAI = Mockito.mock(EmployeeAI.class);

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(pt.up.fe.ldts.model.Vector.LEFT));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(1)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(pt.up.fe.ldts.model.Vector.LEFT, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(new pt.up.fe.ldts.model.Vector(-1, -1)));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(2)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(pt.up.fe.ldts.model.Vector.UP, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(pt.up.fe.ldts.model.Vector.UP));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            employee.setDirection(pt.up.fe.ldts.model.Vector.DOWN);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(3)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(pt.up.fe.ldts.model.Vector.LEFT, employee.getDirection());
        }
    }

    @Test
    public void testMove() {

        Employee employee = new Employee(4, 5, null);

        Assertions.assertEquals(pt.up.fe.ldts.model.Vector.UP, employee.getDirection());

        employee.move();

        Assertions.assertEquals(4, employee.getX());
        Assertions.assertEquals(4, employee.getY());

        employee.setDirection(new pt.up.fe.ldts.model.Vector(5, 5));

        employee.move();

        Assertions.assertEquals(4, employee.getX());
        Assertions.assertEquals(4, employee.getY());

        MapConfiguration.setMapHeight(10);
        MapConfiguration.setMapWidth(10);
        employee.setDirection(pt.up.fe.ldts.model.Vector.NULL);

        employee.changePos(0, 5);
        employee.move();

        Assertions.assertEquals(MapConfiguration.getMapWidth()-1, employee.getX());

        employee.changePos(MapConfiguration.getMapWidth()-1, 5);
        employee.move();

        Assertions.assertEquals(0, employee.getX());

        employee.changePos(5, 0);
        employee.move();

        Assertions.assertEquals(MapConfiguration.getMapHeight()-1, employee.getY());

        employee.changePos(5, MapConfiguration.getMapHeight()-1);
        employee.move();

        Assertions.assertEquals(0, employee.getY());

        MapConfiguration.setGatePosition(new Point(5, 5));
        employee.setCurrentState(Employee.EmployeeState.DEAD);
        employee.changePos(5, 4);

        employee.move();

        Assertions.assertEquals(Vector.DOWN, employee.getDirection());
        Assertions.assertEquals(5, employee.getX());
        Assertions.assertEquals(5, employee.getY());
    }
}