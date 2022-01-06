package pt.up.fe.ldts;

import pt.up.fe.ldts.controller.employeeAI.*;
import pt.up.fe.ldts.model.*;
import pt.up.fe.ldts.model.Point;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final int TICK_TIME = 150;

    private final GUI gui;

    private static final int WIDTH = 27, HEIGTH = 30;

    private Arena arena;

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

    private List<Employee> getMapEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(13, 15, new BaltaAI()));
        employees.add(new Employee(13, 16, new MariAI()));
        employees.add(new Employee(14, 16, new ZeCastroAI(employees.get(0))));
        employees.add(new Employee(12, 16, new ToniAI()));
        return employees;
    }

    public Application() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(WIDTH, HEIGTH+1);

        this.arena = new Arena(WIDTH, HEIGTH);
        this.arena.addWalls(this.getMapWalls(WIDTH, HEIGTH));
        this.arena.addEmployees(this.getMapEmployees());

        Renderer.addViewer(arena);
    }

    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (IOException | FontFormatException | URISyntaxException ignored) {
        }
    }

    private void start() throws IOException {

        boolean running = true;

        int FPS = 60;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();
        while (running) {

            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT:
                    running = false;
                case NONE:
                default:
                    break;
            }

            if (lastTime - startTime > TICK_TIME) {
                this.arena.getEmployees().forEach(employee -> {
                    employee.changeDirection(this.arena);
                    employee.move();
                });
                startTime = lastTime;
            }

            Renderer.render(gui);

            long elapsedTime = System.currentTimeMillis() - lastTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        gui.close();
    }
}
