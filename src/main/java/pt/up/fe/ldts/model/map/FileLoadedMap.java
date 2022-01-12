package pt.up.fe.ldts.model.map;

import pt.up.fe.ldts.controller.employeeAI.BaltaAI;
import pt.up.fe.ldts.controller.employeeAI.MariAI;
import pt.up.fe.ldts.controller.employeeAI.ToniAI;
import pt.up.fe.ldts.controller.employeeAI.ZeCastroAI;
import pt.up.fe.ldts.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileLoadedMap implements Map {

    private final int width, height, boxWidth, boxHeight;

    private final List<Wall> walls;
    private final List<Employee> employees;
    private final List<Collectible> collectibles;

    private Point gatePosition; // can't be final since it is initialized in a switch statement ,so it might not happen
    private final Point boxPosition/*top left corner of ghost box*/;

    public FileLoadedMap(String mapName) throws Exception {
        URL resource = getClass().getClassLoader().getResource("maps/" + mapName + ".map");

        if (resource == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Map file not found: ").append(mapName).append(".map");

            throw new Exception(sb.toString());
        }

        File mapFile = new File(resource.toURI());
        BufferedReader br = new BufferedReader(new FileReader(mapFile));

        Point baltaPos = new Point(-1,-1), zePos = new Point(-1,-1), toniPos = new Point(-1,-1), mariPos = new Point(-1,-1);

        walls = new ArrayList<>();
        employees = new ArrayList<>();
        collectibles = new ArrayList<>();

        String str = br.readLine();
        String[] sr = str.split("X");

        width = Integer.parseInt(sr[0]);
        if (width < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Negative width: ").append(width).append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        height = Integer.parseInt(sr[1]);
        if (height < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Negative height: ").append(height).append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        for(int y = 0; y < this.height; y++){
            str = br.readLine();

            if(str.length() != width){
                StringBuilder sb = new StringBuilder();
                sb.append(mapName).append(".map: ").append("Expected line with width: ").append(width).append(", instead got: ").append(str.length()).append(" - ").append(str);
                throw new Exception(sb.toString());
            }

            for(int x = 0; x < this.width; x++){
                switch (str.charAt(x)){
                    case 'W' -> walls.add(new Wall(x,y));
                    case 't' -> collectibles.add(new Tremoco(x,y));
                    case 'C' -> collectibles.add(new Cerveja(x,y));
                    case 'J' -> Jorge.singleton.changePos(x,y);
                    case 'G' -> gatePosition = new Point(x,y);
                    case 'B' -> baltaPos = new Point(x,y);
                    case 'Z' -> zePos = new Point(x,y);
                    case 'M' -> mariPos = new Point(x,y);
                    case 'T' -> toniPos = new Point(x,y);
                }
            }
        }

        str = br.readLine();
        sr = str.split(",");

        boxPosition = new Point(Integer.parseInt(sr[0]), Integer.parseInt(sr[1]));

        if (boxPosition.getX() < 0 || boxPosition.getY() < 0){
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid box position: (").append(boxPosition.getX()).append(",").append(boxPosition.getY()).append(") - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        str = br.readLine();
        sr = str.split("X");

        boxWidth = Integer.parseInt(sr[0]);

        if (boxWidth < 0){
            StringBuilder sb = new StringBuilder();
            sb.append("Negative box width: ").append(boxWidth).append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }
        boxHeight = Integer.parseInt(sr[1]);

        if (boxHeight < 0){
            StringBuilder sb = new StringBuilder();
            sb.append("Negative box height: ").append(boxHeight).append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (baltaPos.equals(new Point(-1,-1))){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee not found: Balta").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (toniPos.equals(new Point(-1,-1))){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee not found: Toni").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (mariPos.equals(new Point(-1,-1))){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee not found: Mari").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (zePos.equals(new Point(-1,-1))){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee not found: ZeCastro").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        employees.add(new Employee(baltaPos.getX(), baltaPos.getY(), new BaltaAI()));
        employees.add(new Employee(zePos.getX(), zePos.getY(), new ZeCastroAI(employees.get(0))));
        employees.add(new Employee(mariPos.getX(), mariPos.getY(), new MariAI()));
        employees.add(new Employee(toniPos.getX(), toniPos.getY(), new ToniAI()));
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

    @Override
    public int getBoxHeight() {
        return boxHeight;
    }

    @Override
    public int getBoxWidth() {
        return boxWidth;
    }

    @Override
    public Point getBoxPosition() {
        return boxPosition;
    }
}
