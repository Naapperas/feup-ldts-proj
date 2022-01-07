package pt.up.fe.ldts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.model.Cerveja;
import pt.up.fe.ldts.model.Collectible;
import pt.up.fe.ldts.model.Wall;
import pt.up.fe.ldts.model.Point;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationTest {
    @Test
    public void createCollectiblesCorrectly() throws IOException, URISyntaxException, FontFormatException {
        Application app = new Application();
        var spyApp = Mockito.spy(app);
        Mockito.doAnswer(invocation -> {
            List<Wall> walls = new ArrayList<>();
            for (int i = 0; i <= 3; i++){
                walls.add(new Wall(i, 1));
                walls.add(new Wall(i, 5));
            }
            for (int i = 1; i <= 5; i++){
                walls.add(new Wall(0, i));
                walls.add(new Wall(3, i));
            }
            return walls;
        }).when(spyApp).getMapWalls(Mockito.anyInt(), Mockito.anyInt());

        List<Point> wallPos = new ArrayList<>();
        List<Point> collectiblePos = new ArrayList<>();
        for(Wall w : spyApp.getMapWalls(4, 5)){
            wallPos.add(w.getPosition());
        }
        for(Collectible c : spyApp.getMapCollectibles(4, 5)){
            collectiblePos.add(c.getPosition());
        }

        for (Point p: collectiblePos){
            Assertions.assertFalse(wallPos.contains(p));
        }

        Assertions.assertEquals(6, collectiblePos.size());

        Assertions.assertTrue(app.getMapCollectibles(4, 5).contains(new Cerveja(1, 4)));
    }
}
