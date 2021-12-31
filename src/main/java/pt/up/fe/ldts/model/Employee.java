package pt.up.fe.ldts.model;

import pt.up.fe.ldts.Controller.employeeAI.EmployeeAI;

public class Employee extends Entity implements CervejaListener {

    private static int SCORE_WHEN_EATEN = 20;

    public enum EmployeeState {
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

    public void setCurrentState(EmployeeState newState) {
        this.state = newState;
    }

    @Override
    public void cervejaPicked() {

        var currState = this.getCurrentState();
        this.setCurrentState(EmployeeState.FRIGHTENED);

        //TODO: implement timer

        this.setCurrentState(currState);
    }

    @Override
    public void changeDirection() {

        this.setDirection(this.ai.chooseTargetDirection(this.getCurrentState(), this.getPosition(), this.getDirection()));
    }
}
