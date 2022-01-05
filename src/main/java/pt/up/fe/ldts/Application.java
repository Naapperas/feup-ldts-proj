package pt.up.fe.ldts;

import pt.up.fe.ldts.controller.employeeAI.*;
import pt.up.fe.ldts.model.Arena;
import pt.up.fe.ldts.model.Employee;
import pt.up.fe.ldts.model.Wall;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private final GUI gui;
    private Object state;

    private static final int WIDTH = 27, HEIGTH = 30;

    private Arena arena;

    private List<Wall> getMapWalls(int width, int heigth) {
        List<Wall> walls = new ArrayList<>();
        // UPPER AND LOWER BOUNDS
        for (int i = 0; i<=WIDTH; i++)
            walls.add(new Wall(i, 0));
        for (int i = 0; i<=WIDTH; i++)
            walls.add(new Wall(i, 30));

        // SIDES
        for (int i = 0; i<=HEIGTH; i++) {
            if (i == 10 || i == 11 || i == 12 || i == 14|| i == 16 || i == 17 || i == 18)
                continue;
            walls.add(new Wall(0, i));
        }
        for (int i = 0; i<=HEIGTH; i++) {
            if (i == 10 || i == 11 || i == 12 || i == 14|| i == 16 || i == 17 || i == 18)
                continue;
            walls.add(new Wall(26, i));
        }


        // SMALLER PARTS OF THE BORDER
        for (int i = 0; i<=4; i++)
            walls.add(new Wall(13, i));
        walls.add(new Wall(1, 24));
        walls.add(new Wall(1, 25));
        walls.add(new Wall(2, 24));
        walls.add(new Wall(2, 25));
        walls.add(new Wall(24, 24));
        walls.add(new Wall(24, 25));
        walls.add(new Wall(25, 24));
        walls.add(new Wall(25, 25));

        for (int i = 1; i<=5; i++) {
            walls.add(new Wall(i, 9));
            walls.add(new Wall(i, 19));
            walls.add(new Wall(i, 13));
            walls.add(new Wall(i, 15));
        }
        for (int i = 21; i<=25; i++) {
            walls.add(new Wall(i, 9));
            walls.add(new Wall(i, 19));
            walls.add(new Wall(i, 13));
            walls.add(new Wall(i, 15));
        }
        for (int i = 10; i<=12; i++) {
            walls.add(new Wall(5, i));
            walls.add(new Wall(21, i));
        }
        for (int i = 16; i<=19; i++) {
            walls.add(new Wall(5, i));
            walls.add(new Wall(21, i));
        }


        // CENTER
        for (int i = 11; i<=15; i++) {
            if (i == 13)
                continue;
            walls.add(new Wall(i, 13));
        }
        for (int i = 11; i<=15; i++)
            walls.add(new Wall(i, 15));
        walls.add(new Wall(11, 14));
        walls.add(new Wall(15, 14));

        // TOP RECTANGLES
        for (int i = 2; i<=5; i++) {
            walls.add(new Wall(i, 2));
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 6));
            walls.add(new Wall(i, 7));
        }
        for (int i = 21; i<=24; i++) {
            walls.add(new Wall(i, 2));
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
            walls.add(new Wall(i, 6));
            walls.add(new Wall(i, 7));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(i, 2));
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
        }
        for (int i = 7; i<=11; i++) {
            walls.add(new Wall(i, 2));
            walls.add(new Wall(i, 3));
            walls.add(new Wall(i, 4));
        }


        // OTHER STUFF MIDDLE/TOP
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 6));
            walls.add(new Wall(i, 7));
        }
        for (int i = 8; i<=10; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 6; i<=13; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 6; i<=13; i++) {
            walls.add(new Wall(18, i));
            walls.add(new Wall(19, i));
        }
        for (int i = 9; i<=11; i++) {
            walls.add(new Wall(i, 9));
            walls.add(new Wall(i, 10));
        }
        for (int i = 15; i<=17; i++) {
            walls.add(new Wall(i, 9));
            walls.add(new Wall(i, 10));
        }


        // OTHER STUFF MIDDLE/BOTTOM
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 18));
            walls.add(new Wall(i, 19));
        }
        for (int i = 20; i<=22; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(18, i));
            walls.add(new Wall(19, i));
        }


        // OTHER STUFF BOTTOM
        for (int i = 10; i<=16; i++) {
            walls.add(new Wall(i, 24));
            walls.add(new Wall(i, 25));
        }
        for (int i = 26; i<=28; i++) {
            walls.add(new Wall(13, i));
        }
        for (int i = 7; i<=11; i++) {
            walls.add(new Wall(i, 21));
            walls.add(new Wall(i, 22));
        }
        for (int i = 15; i<=19; i++) {
            walls.add(new Wall(i, 21));
            walls.add(new Wall(i, 22));
        }
        for (int i = 2; i<=5; i++) {
            walls.add(new Wall(i, 21));
            walls.add(new Wall(i, 22));
        }
        for (int i = 23; i<=25; i++) {
            walls.add(new Wall(4, i));
            walls.add(new Wall(5, i));
        }
        for (int i = 21; i<=24; i++) {
            walls.add(new Wall(i, 21));
            walls.add(new Wall(i, 22));
        }
        for (int i = 23; i<=25; i++) {
            walls.add(new Wall(21, i));
            walls.add(new Wall(22, i));
        }
        for (int i = 2; i<=11; i++) {
            walls.add(new Wall(i, 27));
            walls.add(new Wall(i, 28));
        }
        for (int i = 24; i<=26; i++) {
            walls.add(new Wall(7, i));
            walls.add(new Wall(8, i));
        }
        for (int i = 15; i<=24; i++) {
            walls.add(new Wall(i, 27));
            walls.add(new Wall(i, 28));
        }
        for (int i = 24; i<=26; i++) {
            walls.add(new Wall(19, i));
            walls.add(new Wall(18, i));
        }

        return walls;
    }

    private List<Employee> getMapEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(13, 14, new BaltaAI()));
        employees.add(new Employee(13, 14, new MariAI()));
        employees.add(new Employee(13, 14, new ZeCastroAI(employees.get(0))));
        employees.add(new Employee(13, 14, new ToniAI()));
        return employees;
    }

    public Application() throws FontFormatException, IOException, URISyntaxException {
        this.gui = new LanternaGUI(WIDTH, HEIGTH);

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
        int FPS = 60;
        int frameTime = 1000 / FPS;

        int test = 0;

        long startTime = System.currentTimeMillis();
        while (test++ <= 500) {

            long lastTime = System.currentTimeMillis();

            if (lastTime - startTime > 500) {
                // update entities
            }

            Renderer.render(gui);
            startTime = lastTime;

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
