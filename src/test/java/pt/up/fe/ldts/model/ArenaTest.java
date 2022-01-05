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
import java.util.List;
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
}
