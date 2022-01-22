package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.view.Drawable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDisplay implements Drawable {

    private final List<Integer> scores = new ArrayList<>();

    public LeaderboardDisplay() {
        try {
            File leaderboard = new File("leaderboard.txt");

            BufferedReader br = new BufferedReader(new FileReader (leaderboard));

            int line = 1;
            String scoreString;

            while ((scoreString = br.readLine()) != null && line++ <= 15)
                this.scores.add(Integer.parseInt(scoreString));

        } catch (IOException e) {
            this.scores.clear();
        }
    }

    @Override
    public void render(TextGraphics tg) {

        tg.putString(14, 0, "LEADERBOARD");

        if (this.scores.size() == 0)
            tg.putString(6, 15, "NO SCORES COULD BE LOADED!");
        else
            for (int i = 0; i < this.scores.size(); i++)
                tg.putString(15, i+3, (i+1) + ") " + scores.get(i));

    }
}
