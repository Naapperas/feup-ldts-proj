package pt.up.fe.ldts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.model.*;
import pt.up.fe.ldts.model.Point;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationTest {
    @Test
    public void createCollectiblesCorrectly() throws IOException, URISyntaxException, FontFormatException {
        Application appMock = Mockito.mock(Application.class);
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
        }).when(appMock).getMapWalls(Mockito.anyInt(), Mockito.anyInt());

        Mockito.when(appMock.getMapCollectibles(4, 5)).thenAnswer(invocation -> {
            List<Collectible> collectibles = new ArrayList<>();

            int width = 4, height = 5;

            List<Point> wallPos = new ArrayList<>();
            for(Wall w : appMock.getMapWalls(width, height)){
                wallPos.add(w.getPosition());
            }

            for (int x = 0; x < width; x++){
                for (int y = 1; y<=height; y++){
                    if (wallPos.contains(new Point(x, y)))
                        continue;
                    if ((x<=5 && y>=11 && y<=19) || (x>=21 && y>=11 && y<=19) || (x>=7 && x<=19 && y>=10 && y<=20))
                        continue;
                    if ((x==1 && y ==4) || (x==1 && y ==(height-6)) || (x==(width-2) && y ==4) || (x==(width-2) && y ==(height-6)))
                        collectibles.add(new Cerveja(x, y));
                    else
                        collectibles.add(new Tremoco(x, y));
                }
            }
            return collectibles;
        });

        List<Point> wallPos = new ArrayList<>();
        List<Point> collectiblePos = new ArrayList<>();
        for(Wall w : appMock.getMapWalls(4, 5)){
            wallPos.add(w.getPosition());
        }
        for(Collectible c : appMock.getMapCollectibles(4, 5)){
            collectiblePos.add(c.getPosition());
        }

        for (Point p: collectiblePos){
            Assertions.assertFalse(wallPos.contains(p));
        }

        Assertions.assertEquals(6, collectiblePos.size());

        Assertions.assertTrue(appMock.getMapCollectibles(4, 5).contains(new Cerveja(1, 4)));
    }
}
