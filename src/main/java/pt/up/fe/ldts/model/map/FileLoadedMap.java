package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.controller.employeeAI.BaltaAI;
import pt.up.fe.ldts.controller.employeeAI.MariAI;
import pt.up.fe.ldts.controller.employeeAI.ToniAI;
import pt.up.fe.ldts.controller.employeeAI.ZeCastroAI;
import pt.up.fe.ldts.model.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileLoadedMap implements Map {

    private int width, height, boxWidth, boxHeight;

    private List<Wall> walls;
    private List<Employee> employees;
    private List<Collectible> collectibles;

    private Point gatePosition, boxPosition; //top left corner of ghost box

    public FileLoadedMap(String mapName) throws Exception {
        URL resource = getClass().getClassLoader().getResource("map/default.map");
        File mapFile = new File(resource.toURI());

        walls = new ArrayList<>();
        employees = new ArrayList<>();
        collectibles = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(mapFile));

        String str = br.readLine();
        String[] sr = str.split("X");

        width = Integer.parseInt(sr[0]);
        height = Integer.parseInt(sr[1]);

        for(int y = 0; y < this.height; y++){
            str = br.readLine();

            if(str.length() != width){
                StringBuilder sb = new StringBuilder();
                sb.append("expected line with width: ").append(width).append(", instead got: ").append(str.length()).append(" - ").append(str);
                throw new Exception(sb.toString());
            }


            for(int x = 0; x < this.width; x++){
                switch (str.charAt(x)){
                    case 'W' -> walls.add(new Wall(x,y));
                    case 'T' -> collectibles.add(new Tremoco(x,y));
                    case 'C' -> collectibles.add(new Cerveja(x,y));
                    case 'J' -> Jorge.singleton.changePos(x,y);
                    case 'G' -> gatePosition = new Point(x,y);
                }
            }
        }

        str = br.readLine();
        sr = str.split(",");

        boxPosition = new Point(Integer.parseInt(sr[0]), Integer.parseInt(sr[0]));

        str = br.readLine();
        sr = str.split("X");

        boxWidth = Integer.parseInt(sr[0]);
        boxHeight = Integer.parseInt(sr[1]);

        employees.add(new Employee(13, 15, new BaltaAI()));
        employees.add(new Employee(13, 16, new MariAI()));
        employees.add(new Employee(14, 16, new ZeCastroAI(employees.get(0))));
        employees.add(new Employee(12, 16, new ToniAI()));
    }

    @Override
    public List<Wall> getMapWalls() {
        return this.walls;
    }

    @Override
    public List<Collectible> getMapCollecibles() {
        return this.collectibles;
    }

    @Override
    public List<Employee> getMapEmployees() {
        return this.employees;
    }

    @Override
    public Point getGatePosition() {
        return this.gatePosition;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

}
