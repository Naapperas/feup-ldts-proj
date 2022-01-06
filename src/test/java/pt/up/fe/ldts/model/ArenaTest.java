package pt.up.fe.ldts.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.model.Arena;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;

import java.nio.file.FileSystemLoopException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ArenaTest {

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
        arena.addWalls(walls);

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
        arena.addCollectibles(collectibles);

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
        arena.addEmployees(employees);

        TextGraphics tg = Mockito.mock(TextGraphics.class);

        arena.render(tg);

        Mockito.verify(tg, Mockito.times(1)).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 20), ' ');

        Assertions.assertEquals(50, wallTest.get());
        Assertions.assertEquals(15, collectibleTest.get());
        Assertions.assertEquals(5, employeeTest.get());
    }

    private List<Wall> getMapWalls(int width, int heigth) {
        List<Wall> walls = new ArrayList<>();
        // UPPER AND LOWER BOUNDS
        for (int i = 0; i<=width; i++)
            walls.add(new Wall(i, 1));
        for (int i = 0; i<=width; i++)
            walls.add(new Wall(i, heigth+1));

        // SIDES
        for (int i = 0; i<=heigth; i++) {
            if (i == 10 || i == 11 || i == 12 || i == 14|| i == 16 || i == 17 || i == 18)
                continue;
            walls.add(new Wall(0, i+1));
        }
        for (int i = 0; i<=heigth; i++) {
            if (i == 10 || i == 11 || i == 12 || i == 14|| i == 16 || i == 17 || i == 18)
                continue;
            walls.add(new Wall(26, i+1));
        }

        // SMALLER PARTS OF THE BORDER
        for (int i = 1; i<=5; i++)
            walls.add(new Wall(13, i));
        walls.add(new Wall(1, 25));
        walls.add(new Wall(1, 26));
        walls.add(new Wall(2, 25));
        walls.add(new Wall(2, 26));
        walls.add(new Wall(24, 25));
        walls.add(new Wall(24, 26));
        walls.add(new Wall(25, 25));
        walls.add(new Wall(25, 26));

        for (int i = 1; i<=5; i++) {
            walls.add(new Wall(i, 10));
            walls.add(new Wall(i, 20));
            walls.add(new Wall(i, 14));
            walls.add(new Wall(i, 16));
        }
        for (int i = 21; i<=25; i++) {
            walls.add(new Wall(i, 10));
            walls.add(new Wall(i, 20));
            walls.add(new Wall(i, 14));
            walls.add(new Wall(i, 16));
        }
        for (int i = 11; i<=13; i++) {
            walls.add(new Wall(5, i));
            walls.add(new Wall(21, i));
        }
        for (int i = 17; i<=20; i++) {
            walls.add(new Wall(5, i));
            walls.add(new Wall(21, i));
        }

        // CENTER
        for (int i = 10; i<=16; i++) {
            if (i == 13)
                continue;
            walls.add(new Wall(i, 13));
        }
        for (int i = 10; i<=16; i++)
            walls.add(new Wall(i, 17));
        for (int i = 14; i<=16; i++)
            walls.add(new Wall(16, i));
        for (int i = 14; i<=16; i++)
            walls.add(new Wall(10, i));

        // TOP RECTANGLES
        for (int i = 2; i<=5; i++) {
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 5));
            walls.add(new Wall(i, 7));
            walls.add(new Wall(i, 8));
        }
        for (int i = 21; i<=24; i++) {
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 5));
            walls.add(new Wall(i, 7));
            walls.add(new Wall(i, 8));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 5));
        }
        for (int i = 7; i<=11; i++) {
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 5));
        }

        // OTHER STUFF MIDDLE/TOP
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 7));
            walls.add(new Wall(i, 8));
        }
        for (int i = 9; i<=11; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 7; i<=14; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 7; i<=14; i++) {
            walls.add(new Wall(18, i));
            walls.add(new Wall(19, i));
        }
        for (int i = 9; i<=11; i++) {
            walls.add(new Wall(i, 10));
            walls.add(new Wall(i, 11));
        }
        for (int i = 15; i<=17; i++) {
            walls.add(new Wall(i, 10));
            walls.add(new Wall(i, 11));
        }

        // OTHER STUFF MIDDLE/BOTTOM
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 19));
            walls.add(new Wall(i, 20));
        }
        for (int i = 21; i<=23; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 16; i<=20; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 16; i<=20; i++) {
            walls.add(new Wall(18, i));
            walls.add(new Wall(19, i));
        }

        // OTHER STUFF BOTTOM
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 25));
            walls.add(new Wall(i, 26));
        }
        for (int i = 27; i<=29; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 7; i<=11; i++) {
            walls.add(new Wall(i, 22));
            walls.add(new Wall(i, 23));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(i, 22));
            walls.add(new Wall(i, 23));
        }
        for (int i = 2; i<=5; i++) {
            walls.add(new Wall(i, 22));
            walls.add(new Wall(i, 23));
        }
        for (int i = 24; i<=26; i++) {
            walls.add(new Wall(4, i));
            walls.add(new Wall(5, i));
        }
        for (int i = 21; i<=24; i++) {
            walls.add(new Wall(i, 22));
            walls.add(new Wall(i, 23));
        }
        for (int i = 24; i<=26; i++) {
            walls.add(new Wall(21, i));
            walls.add(new Wall(22, i));
        }
        for (int i = 2; i<=11; i++) {
            walls.add(new Wall(i, 28));
            walls.add(new Wall(i, 29));
        }
        for (int i = 25; i<=27; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 15; i<=24; i++) {
            walls.add(new Wall(i, 28));
            walls.add(new Wall(i, 29));
        }
        for (int i = 25; i<=27; i++) {
            walls.add(new Wall(19, i));
            walls.add(new Wall(18, i));
        }

        return walls;
    }

    @Test
    public void testValidDirections() {

        Arena arena = new Arena(27, 27);
        List<Wall> walls = this.getMapWalls(27, 27);

        arena.addWalls(walls);

        {
            Jorge.singleton.changePos(6, 6);
            Set<Vector> expected = Set.of(Vector.LEFT, Vector.DOWN, Vector.RIGHT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), false));
        }

        {
            Jorge.singleton.changePos(6, 6);
            Set<Vector> expected = Set.of(Vector.UP, Vector.LEFT, Vector.DOWN, Vector.RIGHT);

            Assertions.assertEquals(expected, arena.getValidDirections(Jorge.singleton.getPosition(), Jorge.singleton.getDirection(), true));
        }
    }
}
