import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
* Project 1 Part 2 CPSC 1220 Intro to Computer Science II.
* Jonathan Elder
* Date 8/20/2022
*/
public class SpherocylinderTest {

    /** Fixture initialization. Common setup for all tests. */
    @Before public void setUp() {
        // No initialization required for these tests.
    }

    /** Tests the setLabel() and getLabel() methods. */
    @Test public void setLabelTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertTrue("Label should be updated successfully.", example1.setLabel(" Medium Example"));
        Assert.assertTrue("Label should contain the updated value.", example1.getLabel().contains("Medium Example"));
        Assert.assertFalse("Label should not be set to null.", example1.setLabel(null));
    }

    /** Tests the setRadius() and getRadius() methods. */
    @Test public void setRadiusTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertTrue("Radius should be updated to a positive value.", example1.setRadius(0.1));
        Assert.assertTrue("Radius should be updated to zero.", example1.setRadius(0));
        Assert.assertFalse("Radius should not accept negative values.", example1.setRadius(-0.1));
        Assert.assertEquals("Radius should be correctly updated.", 0, example1.getRadius(), .000001);
    }

    /** Tests the setCylinderHeight() and getCylinderHeight() methods. */
    @Test public void setCylinderHeightTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertTrue("Cylinder height should be updated to a positive value.", example1.setCylinderHeight(0.1));
        Assert.assertTrue("Cylinder height should be updated to zero.", example1.setCylinderHeight(0));
        Assert.assertFalse("Cylinder height should not accept negative values.", example1.setCylinderHeight(-0.1));
        Assert.assertEquals("Cylinder height should be correctly updated.", 0, example1.getCylinderHeight(), .000001);
    }

    /** Tests the circumference() method. */
    @Test public void circumferenceTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertEquals("Circumference calculation should match the expected value.", 3.141592654, example1.circumference(), .000001);
    }

    /** Tests the surfaceArea() method. */
    @Test public void surfaceAreaTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertEquals("Surface area calculation should match the expected value.", 3.926990817, example1.surfaceArea(), .000001);
    }

    /** Tests the volume() method. */
    @Test public void volumeTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertEquals("Volume calculation should match the expected value.", 0.719948316, example1.volume(), .000001);
    }

    /** Tests the toString() method. */
    @Test public void toStringTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertTrue("String representation should include the label.", example1.toString().contains("Small Example"));
    }

    /** Tests the getCount() method. */
    @Test public void getCountTest() {
        Spherocylinder.resetCount();
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Spherocylinder example2 = new Spherocylinder(" Medium Example ", 10.8, 10.1);
        Assert.assertEquals("Count should reflect the total number of Spherocylinders created.", 2, Spherocylinder.getCount());
    }

    /** Tests the resetCount() method. */
    @Test public void resetCountTest() {
        Spherocylinder.resetCount();
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Spherocylinder example2 = new Spherocylinder(" Medium Example ", 10.8, 10.1);
        Spherocylinder.resetCount();
        Assert.assertEquals("Count should be reset to zero.", 0, Spherocylinder.getCount());
    }

    /** Tests the equals() method. */
    @Test public void equalsTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        String example2 = "hello";
        Spherocylinder example3 = new Spherocylinder("Medium Example", 0.5, 0.25);
        Spherocylinder example4 = new Spherocylinder("Small Example", 0.2, 0.25);
        Spherocylinder example5 = new Spherocylinder("Small Example", 0.5, 0.22);
        Spherocylinder example6 = new Spherocylinder("Small Example", 0.5, 0.25);

        Assert.assertFalse("Equals should return false for non-Spherocylinder objects.", example1.equals(example2));
        Assert.assertFalse("Equals should return false for objects with different labels.", example1.equals(example3));
        Assert.assertFalse("Equals should return false for objects with different radii.", example1.equals(example4));
        Assert.assertFalse("Equals should return false for objects with different heights.", example1.equals(example5));
        Assert.assertTrue("Equals should return true for identical objects.", example1.equals(example6));
    }

    /** Tests the hashCode() method. */
    @Test public void hashCodeTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Assert.assertEquals("Hash code should always return 0 as per the implementation.", 0, example1.hashCode());
    }

    /** Tests the compareTo() method. */
    @Test public void compareToTest() {
        Spherocylinder example1 = new Spherocylinder("Small Example", 0.5, 0.25);
        Spherocylinder example2 = new Spherocylinder(" Medium Example ", 10.8, 10.1);
        Spherocylinder example3 = new Spherocylinder("Small Example", 0.5, 0.25);

        Assert.assertEquals("CompareTo should return 0 for objects with equal volumes.", 0, example1.compareTo(example3));
        Assert.assertEquals("CompareTo should return -1 when the first object has a smaller volume.", -1, example1.compareTo(example2));
        Assert.assertEquals("CompareTo should return 1 when the first object has a larger volume.", 1, example2.compareTo(example1));
    }
}
