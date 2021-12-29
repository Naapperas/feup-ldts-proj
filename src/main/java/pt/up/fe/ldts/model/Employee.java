package pt.up.fe.ldts.model;

import pt.up.fe.ldts.model.employeeAI.EmployeeAI;

public class Employee extends Entity implements CervejaListener {

    private static int SCORE_WHEN_EATEN = 20;

    enum EmployeeState {
        SCATTER,
        FRIGHTENED,
        CHASING,
        DEAD
    }

    private EmployeeAI ai;
    private EmployeeState state = EmployeeState.SCATTER;

    public Employee(int x, int y, EmployeeAI ai) {
        super(x, y);
        this.ai = ai;
    }

    public EmployeeState getCurrentState() {
        return this.state;
    }

    @Override
    public void cervejaPicked() {

    }

    @Override
    public void changeDirection() {

    }
}
