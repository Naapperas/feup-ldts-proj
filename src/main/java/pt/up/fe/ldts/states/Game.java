package pt.up.fe.ldts.states;

import pt.up.fe.ldts.Application;
import pt.up.fe.ldts.model.game.Arena;
import pt.up.fe.ldts.model.game.Jorge;
import pt.up.fe.ldts.model.map.FileLoadedMap;
import pt.up.fe.ldts.model.map.Map;
import pt.up.fe.ldts.model.map.MapConfiguration;
import pt.up.fe.ldts.model.menus.Button;
import pt.up.fe.ldts.view.Renderer;
import pt.up.fe.ldts.view.gui.GUI;
import pt.up.fe.ldts.view.gui.LanternaGUI;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game extends AppState {

    private static final int TICK_TIME = 75;

    private final Arena arena;

    private boolean paused = false;

    private final PauseButton pauseButton = new PauseButton(-1, -1);

    public Game(Application app, String map) throws Exception {
        super(app);

        Map map1 = new FileLoadedMap(map);
        this.arena = new Arena(map1);

        this.gui = new LanternaGUI(MapConfiguration.getMapWidth(), MapConfiguration.getMapHeight() +1);

        Renderer.clearRenderer();
        Renderer.addDrawable(arena);
    }

    @SuppressWarnings("CatchAndPrintStackTrace")
    @Override
    public void start() throws Exception {

        boolean running = true;
        Jorge.singleton.restart();

        int FPS = 8;
        int frameTime = 1000 / FPS;

        long startTime = System.currentTimeMillis();
        while (running && Jorge.singleton.isAlive()) {

            long lastTime = System.currentTimeMillis();

            GUI.ACTION currentAction = gui.getNextAction();

            switch (currentAction) {
                case QUIT -> running = false;
                case PAUSE -> {
                    paused = !paused;
                    Jorge.singleton.cycleAnimation(!paused);
                }
                default -> {}
            }

            if (lastTime - startTime > TICK_TIME) {
                if (!paused)
                    tick(currentAction);
                startTime = lastTime;
            }

            this.render();

            long elapsedTime = System.currentTimeMillis() - lastTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        saveScore();
        gui.close();

        this.app.changeState(new InitialMenu(this.app));
    }

    @Override
    public void render() throws IOException {
        if (paused)
            Renderer.addDrawable(this.pauseButton);
        Renderer.render(gui);
        Renderer.removeDrawable(this.pauseButton);
    }

    private void tick(GUI.ACTION action) {

        Jorge.singleton.chooseDirection(action, this.arena);
        Jorge.singleton.changeDirection(this.arena);

        Jorge.singleton.move();

        this.arena.checkEmployeeCollision();

        this.arena.getEmployees().forEach(employee -> {
            employee.changeDirection(this.arena);
            employee.move();
        });

        this.arena.checkCollectibleCollision();
        this.arena.checkEmployeeCollision();

        if(this.arena.emptyCollectibles())
            this.arena.restartLevel();
    }

    private static class PauseButton extends Button {

        public PauseButton(int x, int y) {
            super(x, y, "PAUSED\nPRESS 'P' TO RESUME\nPRESS 'Q' TO QUIT");
        }
    }

    private void saveScore() throws IOException {
        List<Integer> scores = new ArrayList<>();

        File leaderboard = new File("leaderboard.txt");
        if(leaderboard.createNewFile()){
            scores.add(Jorge.singleton.getScore());
        }
        else{
            BufferedReader br = new BufferedReader(new FileReader(String.valueOf(leaderboard.toPath()), Charset.defaultCharset()));
            String score;

            while((score = br.readLine()) != null){
                scores.add(Integer.parseInt(score));
            }

            scores.add(Jorge.singleton.getScore());
            scores.sort(Collections.reverseOrder());
        }

        scores = scores.stream()
                .distinct()
                .collect(Collectors.toList());

        Writer fw = Files.newBufferedWriter(leaderboard.toPath(), Charset.defaultCharset());

        for (Integer i : scores){
            fw.write(i + "\n");
        }

        fw.close();
    }
}
