package pt.up.fe.ldts.model.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.model.*;

public class FileLoadedMapTest {

    @Test
    public void testInvalidMapName() {

        try {
            new FileLoadedMap("notFound");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Map file not found: notFound.map", e.getMessage());
        }
    }

    @Test
    public void testInvalidLineWidth() {

        try {
            new FileLoadedMap("invalidWidth");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("invalidWidth.map: Expected line with width: 27, instead got: 22 -      WtWWWWW W WWWWWtW", e.getMessage());
        }
    }

    @Test
    public void testInvalidMapWidth() {

        try {
            new FileLoadedMap("invalidDimensionsX");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Negative width: -1 - invalidDimensionsX.map", e.getMessage());
        }
    }

    @Test
    public void testInvalidLineHeight() {

        try {
            new FileLoadedMap("invalidDimensionsY");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Negative height: -1 - invalidDimensionsY.map", e.getMessage());
        }
    }

    @Test
    public void testLoadBaltaPosition() {

        try {
            new FileLoadedMap("defaultNoB");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Employee not found: Balta - defaultNoB.map", e.getMessage());
        }
    }

    @Test
    public void testLoadToniPosition() {

        try {
            new FileLoadedMap("defaultNoT");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Employee not found: Toni - defaultNoT.map", e.getMessage());
        }
    }

    @Test
    public void testLoadZeCastroPosition() {

        try {
            new FileLoadedMap("defaultNoZ");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Employee not found: ZeCastro - defaultNoZ.map", e.getMessage());
        }
    }

    @Test
    public void testLoadMariPosition() {

        try {
            new FileLoadedMap("defaultNoM");
            Assertions.fail(); // should never reach this line
        } catch (Exception e) {
            Assertions.assertEquals("Employee not found: Mari - defaultNoM.map", e.getMessage());
        }
    }

    @Test
    public void testInvalidBoxPosition(){
        try {
            new FileLoadedMap("invalidBox");
            Assertions.fail(); // should never reach this line
        } catch (Exception e){
            Assertions.assertEquals("Invalid box position: (-1,13) - invalidBox.map", e.getMessage());
        }
    }

    @Test
    public void testInvalidBoxHeight(){
        try {
            new FileLoadedMap("invalidBoxDimensionsY");
            Assertions.fail(); // should never reach this line
        } catch (Exception e){
            Assertions.assertEquals("Negative box height: -1 - invalidBoxDimensionsY.map", e.getMessage());
        }
    }

    @Test
    public void testInvalidBoxWidth(){
        try {
            new FileLoadedMap("invalidBoxDimensionsX");
            Assertions.fail(); // should never reach this line
        } catch (Exception e){
            Assertions.assertEquals("Negative box width: -1 - invalidBoxDimensionsX.map", e.getMessage());
        }
    }

    @Test
    public void testMapCreation() throws Exception {

        Map m = new FileLoadedMap("default");

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

        Assertions.assertEquals(new Point(11,13), m.getBoxPosition());

        Assertions.assertEquals(5, m.getBoxWidth());
        Assertions.assertEquals(3, m.getBoxHeight());
    }

}
