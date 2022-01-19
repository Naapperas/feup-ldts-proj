package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.view.Drawable;

import java.io.*;

public class LeaderboardDisplay implements Drawable {
    @Override
    public void render(TextGraphics tg) throws IOException {
        File leaderboard = new File("leaderboard.txt");

        BufferedReader br = new BufferedReader(new FileReader (leaderboard));

        int line = 1;
        String score;

        tg.putString(14, 0, "LEADERBOARD");

        while ((score = br.readLine()) != null && line < 15){
            tg.putString(17, line+3, score);
            line++;
        }
    }
}
