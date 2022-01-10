package pt.up.fe.ldts.model.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.*;

public class DefaultMapTest {

    Map m;

    @BeforeEach
    void setup() {
        m = new DefaultMap();
    }

    @Test
    public void testMapDimensions() {
        Assertions.assertEquals(27, m.getWidth());
        Assertions.assertEquals(30, m.getHeight());
    }

    @Test
    public void testWallCreation() {

    }

    @Test
    public void testEmployeeCreation() {

        var employees = m.getMapEmployees();

        Assertions.assertNotNull(employees);
        Assertions.assertEquals(4, employees.size());

        employees.forEach(employee -> Assertions.assertEquals(Vector.UP, employee.getDirection()));
    }

    @Test
    public void testCollectibleCreation() {

        // maybe figure out a better way to test this

        var collectibles = m.getMapCollecibles();

        Assertions.assertNotNull(collectibles);
        Assertions.assertEquals(243, collectibles.size());

        var wallPos = m.getMapWalls();

        for (int x = 0; x < m.getWidth(); x++){
            for (int y = 1; y<=m.getHeight(); y++){

                if (wallPos.contains(new Wall(x, y)))
                    continue;

                if ((x<=5 && y>=11 && y<=19) || (x>=21 && y>=11 && y<=19) || (x>=7 && x<=19 && y>=10 && y<=20))
                    continue;

                if ((x==1 && y ==4) || (x==1 && y ==(m.getHeight()-6)) || (x==(m.getWidth()-2) && y ==4) || (x==(m.getWidth()-2) && y ==(m.getHeight()-6)))
                    Assertions.assertTrue(collectibles.contains(new Cerveja(x, y)));
                else
                    Assertions.assertTrue(collectibles.contains(new Tremoco(x, y)));
            }
        }
    }
}
