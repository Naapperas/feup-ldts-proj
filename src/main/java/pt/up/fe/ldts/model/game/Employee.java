package pt.up.fe.ldts.model.game;

import com.github.javaparser.utils.Pair;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.controller.employeeAI.EmployeeAI;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee extends Entity implements CervejaListener {

    public static final int SCORE_WHEN_EATEN = 200;
    public static int TIME_FRIGHTENED = 5; // in seconds

    private final FrightenedEmployeeTimer timer = new FrightenedEmployeeTimer(TIME_FRIGHTENED, (previousState) -> {
        if (this.getCurrentState() == EmployeeState.DEAD) return;

        this.setDirection(this.getDirection().multiply(-1));
        this.setCurrentState(previousState);
    });

    @Override
    public void render(TextGraphics tg) {
        var previousForegroundColor = tg.getForegroundColor();

        tg.setForegroundColor(this.getCurrentState() == EmployeeState.FRIGHTENED ? TextColor.Factory.fromString("#0055ff") : this.ai.getEmployeeColor());

        if(this.getCurrentState() == EmployeeState.DEAD)
            tg.putString(this.getX(), this.getY()+1, "j");
        else
            tg.putString(this.getX(), this.getY()+1, "d");

        tg.setForegroundColor(previousForegroundColor);
    }

    public enum EmployeeState {
        SCATTER,
        FRIGHTENED,
        CHASING,
        DEAD
    }

    private final EmployeeAI ai;
    private EmployeeState state = EmployeeState.SCATTER;

    /**
     * Constructs a new Employee on the given position, with a certain EmployeeAi
     * @param x the x coordinate of this Employee
     * @param y the y coordinate of this Employee
     * @param ai EmployeeAi used by this employee
     */
    public Employee(int x, int y, EmployeeAI ai) {
        super(x, y);
        this.ai = ai;

        Thread t = new Thread(() -> {

            boolean firstRun = true;

            while (Thread.currentThread().isAlive()) {
                if (timer.isRunning()) continue;

                int sleepAmount;

                if (firstRun) {
                    sleepAmount = 5;
                    firstRun = false;
                } else
                    sleepAmount = 10;

                try {
                    Thread.sleep(sleepAmount * 1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                this.state = switch (this.getCurrentState()) {
                    case CHASING -> EmployeeState.SCATTER;
                    case SCATTER -> EmployeeState.CHASING;
                    default -> this.getCurrentState();
                };
            }
        });
        t.setDaemon(true);
        t.start();
    }

    /**
     * Get the current state(EmployeeState) of an employee
     * @return the current state of this employee
     */
    public EmployeeState getCurrentState() {
        return this.state;
    }

    /**
     * Set the current state of an employee
     * @param newState new EmployeeState
     */
    public void setCurrentState(EmployeeState newState) {
        this.state = newState;
    }

    @Override
    public void cervejaPicked() {

        switch (this.getCurrentState()) {
            case FRIGHTENED -> timer.addDelay(TIME_FRIGHTENED);
            case DEAD -> {}
            default -> {
                var currState = this.getCurrentState();
                this.setCurrentState(EmployeeState.FRIGHTENED);
                this.setDirection(this.getDirection().multiply(-1));

                timer.start(currState);
            }
        }
    }

    @Override
    public void changeDirection(Arena arena)  {

        Point targetPoint;

        if (arena.isInsideBox(this.getPosition()))
            targetPoint = arena.getGatePosition().addVector(Vector.UP); // make them leave the box initially
        else
            targetPoint = this.ai.chooseTargetPosition(this.getCurrentState(), this.getPosition());

        this.setDirection(this.chooseNextDirection(arena, targetPoint));
    }

    /**
     * Choose the direction of an employee according to the position it's targeting
     * @param targetPoint the position this employee is targeting
     * @return new direction as vector
     */
    private Vector chooseNextDirection(Arena arena, Point targetPoint) {

        var possibleDirections = arena.getValidDirections(this.getPosition(), this.getDirection(), false);
        List<Pair<Vector, Double>> directionPairs = new ArrayList<>();

        for (var direction : possibleDirections)
            directionPairs.add(new Pair<>(direction, Vector.from(this.getPosition().addVector(direction), targetPoint).magnitude()));

        directionPairs.sort((pair1, pair2) -> {

            double mag1 = pair1.b, mag2 = pair2.b;

            return mag1 == mag2 ? pair2.a.compareTo(pair1.a) : Double.compare(mag1, mag2);
        });

        return directionPairs.get(0).a;
    }

    @Override
    public void move(){
        if (!(this.direction.equals(Vector.UP) || this.direction.equals(Vector.DOWN) || this.direction.equals(Vector.LEFT)|| this.direction.equals(Vector.RIGHT) || this.direction.equals(Vector.NULL)))
            return; // unknown direction

        if(this.getCurrentState() == EmployeeState.DEAD) {
            if (this.getPosition().equals(MapConfiguration.getGatePosition().addVector(Vector.UP)))
                this.setDirection(Vector.DOWN);
            else if (this.getPosition().equals(MapConfiguration.getGatePosition().addVector(Vector.DOWN))) {
                this.setDirection(Vector.UP);
                this.setCurrentState(EmployeeState.SCATTER);
                this.timer.cancel();
            }
        }

        var newPos = this.getPosition().addVector(this.direction);

        if (newPos.getY() == 0)
            newPos.setY(MapConfiguration.getMapHeight() -1);
        else if (newPos.getY() == MapConfiguration.getMapHeight()-1)
            newPos.setY(0);
        else if (newPos.getX() == 0)
            newPos.setX(MapConfiguration.getMapWidth()-1);
        else if (newPos.getX() == MapConfiguration.getMapWidth()-1)
            newPos.setX(0);

        this.changePos(newPos.getX(), newPos.getY());
    }
}

class FrightenedEmployeeTimer {

    private volatile boolean running = false;

    public synchronized boolean isRunning() {
        return this.running;
    }

    private final AtomicInteger timeDelay;
    private final int initialDelay;

    @FunctionalInterface
    interface TimerRunnable {
        void run(Employee.EmployeeState previousState);
    }

    TimerRunnable action;

    public FrightenedEmployeeTimer(int initialDelay, TimerRunnable r) {
        this.initialDelay = initialDelay;
        this.timeDelay = new AtomicInteger(this.initialDelay);
        this.action = r;
    }

    public void addDelay(int delay) {
        timeDelay.addAndGet(delay);
    }

    public void cancel() {
        this.timeDelay.set(0);
        this.running = false;
    }

    public void start(Employee.EmployeeState previousState) {

        this.running = true;

        Thread runner = new Thread(() -> {

            int i = 0;

            while (i++ < timeDelay.get())
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            this.timeDelay.set(this.initialDelay);
            this.action.run(previousState);

            this.running = false;
        });
        runner.setDaemon(true);

        runner.start();
    }
}
