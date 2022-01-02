package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;

public class EmployeeTest {

    @Test
    public void testConstruction() {

        Employee employee = new Employee(4, 5, null);

        Assertions.assertEquals(4, employee.getX());
        Assertions.assertEquals(5, employee.getY());

        Assertions.assertEquals(Employee.EmployeeState.SCATTER, employee.getCurrentState());
    }

    @Test
    public void testNotifiable() {

        Point basePoint = new Point(4, 5);

        Employee employee;

        EmployeeAI testAI = Mockito.mock(EmployeeAI.class);

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(Vector.LEFT));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            employee.changeDirection();

            Mockito.verify(testAI, Mockito.times(1)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.LEFT, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(new Vector(-1, -1)));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            employee.changeDirection();

            Mockito.verify(testAI, Mockito.times(2)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.UP, employee.getDirection());
        }

        {
            Mockito.when(testAI.chooseTargetPosition(Mockito.any(), Mockito.any())).thenAnswer(invocation -> basePoint.addVector(Vector.UP));

            employee = new Employee(basePoint.getX(), basePoint.getY(), testAI);

            employee.setDirection(Vector.DOWN);

            employee.changeDirection();

            Mockito.verify(testAI, Mockito.times(3)).chooseTargetPosition(Mockito.any(), Mockito.any());
            Assertions.assertEquals(Vector.LEFT, employee.getDirection());
        }
    }
}