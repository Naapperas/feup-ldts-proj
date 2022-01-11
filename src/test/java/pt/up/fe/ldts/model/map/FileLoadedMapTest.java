package pt.up.fe.ldts.model.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.*;

import java.util.List;

public class FileLoadedMapTest {

    private Map m;

    @BeforeEach
    void setup() throws Exception {
        m = new FileLoadedMap("default");
    }

    @Test
    public void testMapCreation(){

        Assertions.assertEquals(27, m.getWidth());
        Assertions.assertEquals(31, m.getHeight());

        Assertions.assertTrue(m.getMapWalls().contains(new Wall(0,0)));
        Assertions.assertTrue(m.getMapWalls().contains(new Wall(24,4)));

        Assertions.assertTrue(m.getMapCollecibles().contains(new Cerveja(1,3)));
        Assertions.assertTrue(m.getMapCollecibles().contains(new Cerveja(25,3)));

        Assertions.assertTrue(m.getMapCollecibles().contains(new Tremoco(1,2)));
        Assertions.assertTrue(m.getMapCollecibles().contains(new Tremoco(2,23)));

        Assertions.assertEquals(new Point(13,23), Jorge.singleton.getPosition());

        Assertions.assertEquals(new Point(13,12), m.getGatePosition());

        var employees = m.getMapEmployees();

        Assertions.assertNotNull(employees);
        Assertions.assertEquals(4, employees.size());

        employees.forEach(employee -> Assertions.assertEquals(Vector.UP, employee.getDirection()));
    }

}
