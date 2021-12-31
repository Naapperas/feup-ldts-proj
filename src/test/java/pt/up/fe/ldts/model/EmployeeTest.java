package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.Controller.employeeAI.EmployeeAI;

import java.util.concurrent.atomic.AtomicInteger;

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

        Employee employee;

        AtomicInteger test = new AtomicInteger(0);

        EmployeeAI testAI = Mockito.mock(EmployeeAI.class);
        Mockito.when(testAI.chooseTargetDirection(Mockito.any(), Mockito.any(), Mockito.any())).thenAnswer(invocation -> {
            test.set(1);
            return Vector.LEFT;
        });

        employee = new Employee(4, 5, testAI);

        employee.changeDirection();

        Mockito.verify(testAI, Mockito.times(1)).chooseTargetDirection(Mockito.any(), Mockito.any(), Mockito.any());
        Assertions.assertEquals(1, test.get());
        Assertions.assertEquals(Vector.LEFT, employee.getDirection());
    }
}
