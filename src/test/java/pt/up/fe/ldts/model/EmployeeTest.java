package pt.up.fe.ldts.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.model.employeeAI.EmployeeAI;

import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeTest {

    @Test
    public void testCoords() {

        Employee employee = new Employee(4, 5, null);

        Assertions.assertEquals(4, employee.getX());
        Assertions.assertEquals(5, employee.getY());

    }

    @Test
    public void testNotifiable() {

        Employee employee;

        AtomicInteger test = new AtomicInteger();

        EmployeeAI testAI = Mockito.mock(EmployeeAI.class);
        Mockito.doAnswer(invocation -> {
            test.set(1);
            return null;
        }).when(testAI).chooseTargetLocation();

        employee = new Employee(4, 5, testAI);

        employee.cervejaPicked();

        Mockito.verify(testAI, Mockito.times(1)).chooseTargetLocation();
        Assertions.assertEquals(1, test.get());
    }
}
