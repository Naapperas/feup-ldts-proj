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
    private Point baltaPos, zePos, toniPos, mariPos;
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

        if (baltaPos != null && isNotInsideBox(baltaPos)){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee outside box: Balta").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (toniPos != null && isNotInsideBox(toniPos)){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee outside box: Toni").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (mariPos != null && isNotInsideBox(mariPos)){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee outside box: Mari").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if (zePos != null && isNotInsideBox(zePos)){
            StringBuilder sb = new StringBuilder();
            sb.append("Employee outside box: ZeCastro").append(" - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }

        if(zePos != null && baltaPos == null){
            StringBuilder sb = new StringBuilder();
            sb.append("ZeCastro can't exist without Balta - ").append(mapName).append(".map");
            throw new Exception(sb.toString());
        }
        MapConfiguration.map.setMapHeight(height);
        MapConfiguration.map.setMapWidth(width);
        MapConfiguration.map.setBoxPosition(boxPosition);

        if (baltaPos != null)
            employees.add(new Employee(baltaPos.getX(), baltaPos.getY(), new BaltaAI()));
        if (zePos != null)
            employees.add(new Employee(zePos.getX(), zePos.getY(), new ZeCastroAI(employees.get(0))));
        if (mariPos != null)
            employees.add(new Employee(mariPos.getX(), mariPos.getY(), new MariAI()));
        if (toniPos != null)
            employees.add(new Employee(toniPos.getX(), toniPos.getY(), new ToniAI()));


    }

    private boolean isNotInsideBox(Point position) {

        boolean insideWidth = this.boxPosition.getX() <= position.getX() && position.getX() < this.boxPosition.getX() + this.boxWidth;
        boolean insideHeight = this.boxPosition.getY() <= position.getY() && position.getY() < this.boxPosition.getY() + this.boxHeight;

        return !(insideWidth && insideHeight);
    }

    @Override
    public List<Wall> getMapWalls() {
        return this.walls;
    }

    @Override
    public List<Collectible> getMapCollectibles() {
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
