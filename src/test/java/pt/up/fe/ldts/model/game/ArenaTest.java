package pt.up.fe.ldts.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.controller.employeeAI.ToniAI;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.model.Vector;
import pt.up.fe.ldts.model.map.FileLoadedMap;
import pt.up.fe.ldts.model.map.Map;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ArenaTest {

    Map m;
    Arena arena;

    @BeforeEach
    public void setup() throws Exception {
        m = new FileLoadedMap("default");
        arena = new Arena(m);
    }

    @Test
    public void measureTest(){
        Arena arena = new Arena(30, 50);

        Assertions.assertEquals(30, arena.getWidth());
        Assertions.assertEquals(50, arena.getHeight());
    }

    @Test
    public void testRender() {

        Arena arena = new Arena(20, 20);

        AtomicInteger wallTest = new AtomicInteger(0);
        List<Wall> walls = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            var wall = Mockito.mock(Wall.class);
            Mockito.doAnswer(invocation -> {
                wallTest.getAndIncrement();
                return null;
            }).when(wall).render(Mockito.any());
            walls.add(wall);
        }
        arena.setWalls(walls);

        AtomicInteger collectibleTest = new AtomicInteger(0);
        List<Collectible> collectibles = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            var collectible = Mockito.mock(Collectible.class);
            Mockito.doAnswer(invocation -> {
                collectibleTest.getAndIncrement();
                return null;
            }).when(collectible).render(Mockito.any());
            collectibles.add(collectible);
        }
        arena.setCollectibles(collectibles);

        AtomicInteger employeeTest = new AtomicInteger(0);
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            var employee = Mockito.mock(Employee.class);
            Mockito.doAnswer(invocation -> {
                employeeTest.getAndIncrement();
                return null;
            }).when(employee).render(Mockito.any());
            employees.add(employee);
        }
        arena.setEmployees(employees);

        TextGraphics tg = Mockito.mock(TextGraphics.class);

        arena.setGatePosition(new Point(0, 0));

        arena.render(tg);

        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 20), ' ');

        Assertions.assertEquals(50, wallTest.get());
        Assertions.assertEquals(15, collectibleTest.get());
        Assertions.assertEquals(5, employeeTest.get());
    }


    @Test
    public void testValidDirections() {

        Jorge.singleton.setDirection(Vector.UP);

        {
            Jorge.singleton.changePos(6, 5);
            Set<Vector> expected = Set.of(Vector.UP, Vector.LEFT, Vector.RIGHT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), false));
        }

        {
            Jorge.singleton.changePos(6, 5);
            Set<Vector> expected = Set.of(Vector.UP, Vector.LEFT, Vector.DOWN, Vector.RIGHT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), true));
        }

        {
            Jorge.singleton.changePos(7, 5);
            Jorge.singleton.setDirection(Vector.RIGHT);
            Set<Vector> expected = Set.of(Vector.RIGHT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), false));
        }

        {
            Jorge.singleton.changePos(13, 11);
            Jorge.singleton.setDirection(Vector.RIGHT);
            Set<Vector> expected = Set.of(Vector.RIGHT, Vector.LEFT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), true));
        }
    }

    @Test
    public void testAddListeners(){
        Arena arena = new Arena(27, 27);
        arena.setEmployees(m.getMapEmployees());

        List<Collectible> test = new ArrayList<>();
        test.add(new Tremoco(1, 1));
        Cerveja cerveja = new Cerveja(1, 2);
        test.add(cerveja);
        arena.setCollectibles(test);

        Assertions.assertEquals(4, cerveja.listeners.size());
    }

    @Test
    public void testInsideBox() throws Exception {

        Arena a = new Arena(new FileLoadedMap("default"));

        Assertions.assertTrue(a.isInsideBox(new Point(14, 14)));
        Assertions.assertFalse(a.isInsideBox(new Point(1, 1)));
        Assertions.assertFalse(a.isInsideBox(new Point(10, 12)));
        Assertions.assertFalse(a.isInsideBox(new Point(16, 16)));
        Assertions.assertTrue(a.isInsideBox(new Point(15, 15)));
    }

    @Test
    public void testEmployeeCollision(){
        Arena a = new Arena(27,27);
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(10,10, new ToniAI()));
        employees.add(new Employee(13,14, new ToniAI()));

        employees.get(0).setCurrentState(Employee.EmployeeState.FRIGHTENED);
        employees.get(1).setCurrentState(Employee.EmployeeState.FRIGHTENED);

        a.setEmployees(employees);

        Jorge.singleton.addPoints(-Jorge.singleton.getScore());

        Jorge.singleton.changePos(10,10);

        a.checkEmployeeCollision();

        Assertions.assertEquals(Employee.EmployeeState.DEAD, employees.get(0).getCurrentState());
        Assertions.assertEquals(200, Jorge.singleton.getScore());

        Jorge.singleton.changePos(13,14);

        a.checkEmployeeCollision();

        Assertions.assertEquals(Employee.EmployeeState.DEAD, employees.get(1).getCurrentState());
        Assertions.assertEquals(400, Jorge.singleton.getScore());

    }

    @Test
    public void testCollectibleCollision(){
        Arena a = new Arena(27, 27);
        Jorge.singleton.addPoints(-Jorge.singleton.getScore());

        List<Collectible> collectibles = new ArrayList<>();

        collectibles.add(new Tremoco(5,7));
        collectibles.add(new Cerveja(8,9));

        a.setCollectibles(collectibles);

        Jorge.singleton.changePos(5,7);

        Assertions.assertEquals(2, a.getCollectibles().size());

        a.checkCollectibleCollision();

        Assertions.assertEquals(1, a.getCollectibles().size());
        Assertions.assertEquals(10, Jorge.singleton.getScore());

        Jorge.singleton.changePos(8,9);

        a.checkCollectibleCollision();

        Assertions.assertEquals(0, a.getCollectibles().size());
        Assertions.assertEquals(30, Jorge.singleton.getScore());
    }

    @Test
    public void testResetEntities(){
        Arena a = new Arena(27,27);
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(10,10, new ToniAI()));
        employees.add(new Employee(13,14, new ToniAI()));

        employees.get(0).setCurrentState(Employee.EmployeeState.CHASING);
        employees.get(1).setCurrentState(Employee.EmployeeState.SCATTER);

        a.setEmployees(employees);

        Jorge.singleton.addPoints(-Jorge.singleton.getScore());

        Jorge.singleton.changePos(10,10);

        MapConfiguration.setJorgePosition(new Point(5, 5));
        MapConfiguration.setGatePosition(new Point(0, 0));

        a.checkEmployeeCollision(); // resetEntities() called here

        Assertions.assertEquals(MapConfiguration.getGatePosition().addVector(Vector.DOWN), employees.get(0).getPosition());
        Assertions.assertEquals(MapConfiguration.getGatePosition().addVector(Vector.DOWN), employees.get(1).getPosition());

        Assertions.assertEquals(Employee.EmployeeState.SCATTER, employees.get(0).getCurrentState());
        Assertions.assertEquals(Employee.EmployeeState.SCATTER, employees.get(1).getCurrentState());

        Assertions.assertEquals(Vector.UP, employees.get(0).getDirection());
        Assertions.assertEquals(Vector.UP, employees.get(1).getDirection());

        Assertions.assertEquals(MapConfiguration.getJorgePosition(), Jorge.singleton.getPosition());

    }

    @Test
    public void testEmptyCollectibles(){

        Assertions.assertFalse(arena.emptyCollectibles());

        arena.getCollectibles().removeAll(arena.getCollectibles());

        Assertions.assertTrue(arena.emptyCollectibles());

        arena.restartLevel();

        Assertions.assertFalse(arena.emptyCollectibles());
    }
}
