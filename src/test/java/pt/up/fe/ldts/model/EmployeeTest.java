package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmployeeTest {

    @Test
    public void testConstruction() {

        Employee employee = new Employee(4, 5, null);

        Assertions.assertEquals(4, employee.getX());
        Assertions.assertEquals(5, employee.getY());

        Assertions.assertEquals(Employee.EmployeeState.SCATTER, employee.getCurrentState());
    }

    private List<Vector> possibleDirections(Vector direction){
        List<Vector> directions = new ArrayList<>();

        if (Vector.UP.equals(direction)) {
            directions.add(Vector.UP);
            directions.add(Vector.LEFT);
            directions.add(Vector.RIGHT);
        } else if (Vector.LEFT.equals(direction)) {
            directions.add(Vector.LEFT);
            directions.add(Vector.DOWN);
            directions.add(Vector.UP);
        } else if (Vector.DOWN.equals(direction)) {
            directions.add(Vector.DOWN);
            directions.add(Vector.RIGHT);
            directions.add(Vector.LEFT);
        } else if (Vector.RIGHT.equals(direction)) {
            directions.add(Vector.RIGHT);
            directions.add(Vector.UP);
            directions.add(Vector.DOWN);
        }
        return directions;
    }

    @Test
    public void testNotifiable() {

        Point basePoint = new Point(4, 5);

        Employee employee;

        EmployeeAI testAI = Mockito.mock(EmployeeAI.class);

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(Vector.LEFT));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(1)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.LEFT, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(new Vector(-1, -1)));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(2)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.UP, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(Vector.UP));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            employee.setDirection(Vector.DOWN);

            Arena arena = Mockito.mock(Arena.class);
            Mockito.when(arena.getValidDirections(Mockito.any(), Mockito.any(), Mockito.anyBoolean())).thenReturn(Set.copyOf(this.possibleDirections(employee.getDirection())));

            employee.changeDirection(arena);

            Mockito.verify(testAI, Mockito.times(3)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.LEFT, employee.getDirection());
        }
    }
}