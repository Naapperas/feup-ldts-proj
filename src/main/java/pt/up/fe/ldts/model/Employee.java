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
        this.setCurrentState(EmployeeState.FRIGHTENED);

        var currentAi = this.ai;
        this.ai = FrightenedAI.singleton;

        //TODO: implement timer

        this.ai = currentAi;
    }

    @Override
    public void changeDirection() {
        this.setDirection(this.ai.chooseTargetDirection(this.getCurrentState()));
    }
}
