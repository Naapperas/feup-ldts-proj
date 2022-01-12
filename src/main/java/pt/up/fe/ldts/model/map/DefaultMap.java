package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.controller.employeeAI.BaltaAI;
import pt.up.fe.ldts.controller.employeeAI.MariAI;
import pt.up.fe.ldts.controller.employeeAI.ToniAI;
import pt.up.fe.ldts.controller.employeeAI.ZeCastroAI;
import pt.up.fe.ldts.model.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultMap implements Map{

    @Override
    public List<Wall> getMapWalls() { //FIXME: find a better way to handle this
        List<Wall> walls = new ArrayList<>();
        // UPPER AND LOWER BOUNDS
        for (int i = 0; i<=this.getWidth(); i++)
            walls.add(new Wall(i, 1));
        for (int i = 0; i<=this.getWidth(); i++)
            walls.add(new Wall(i, this.getHeight()+1));

        // SIDES
        for (int i = 0; i<=this.getHeight(); i++) {
            if (i == 10 || i == 11 || i == 12 || i == 14|| i == 16 || i == 17 || i == 18)
                continue;
            walls.add(new Wall(0, i+1));
        }
        for (int i = 0; i<=this.getHeight(); i++) {
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

    @Override
    public List<Collectible> getMapCollecibles() {
        List<Collectible> collectibles = new ArrayList<>();

        List<Point> wallPos = new ArrayList<>();
        for(Wall w : this.getMapWalls()){
            wallPos.add(w.getPosition());
        }

        for (int x = 0; x < this.getWidth(); x++){
            for (int y = 1; y<=this.getHeight(); y++){
                if (wallPos.contains(new Point(x, y)))
                    continue;
                if ((x<=5 && y>=11 && y<=19) || (x>=21 && y>=11 && y<=19) || (x>=7 && x<=19 && y>=10 && y<=20))
                    continue;
                if ((x==1 && y ==4) || (x==1 && y ==(this.getHeight()-6)) || (x==(this.getWidth()-2) && y ==4) || (x==(this.getWidth()-2) && y ==(this.getHeight()-6)))
                    collectibles.add(new Cerveja(x, y));
                else
                    collectibles.add(new Tremoco(x, y));
            }
        }
        return collectibles;
    }

    @Override
    public List<Employee> getMapEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(13, 15, new BaltaAI()));
        employees.add(new Employee(13, 16, new MariAI()));
        employees.add(new Employee(14, 16, new ZeCastroAI(employees.get(0))));
        employees.add(new Employee(12, 16, new ToniAI()));
        return employees;
    }

    @Override
    public Point getGatePosition() {
        return new Point(13, 13);
    }

    @Override
    public Point getBoxPosition() {
        return new Point(11, 14);
    }

    @Override
    public int getWidth() {
        return 27;
    }

    @Override
    public int getHeight() {
        return 30;
    }

    @Override
    public int getBoxWidth() {
        return 5;
    }

    @Override
    public int getBoxHeight() {
        return 3;
    }
}
