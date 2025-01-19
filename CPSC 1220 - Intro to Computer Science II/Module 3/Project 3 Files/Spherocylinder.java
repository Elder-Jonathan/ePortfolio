import java.text.DecimalFormat;

/**
* Project 1 Part 1 CPSC 1220 Intro to Computer Science II
* Jonathan Elder
* Date 8/20/2022
*/
public class Spherocylinder implements Comparable<Spherocylinder> {

    // Instance variables
    private String label = "";
    private double radius = 0;
    private double cylinderHeight = 0;
    private static int count = 0;

    /**
    * Constructor for Spherocylinder.
    * Contructor that initializes a Spherocylinder object with a label, radius, and height.
    * @param labelIn Input label for the spherocylinder.
    * @param radiusIn Radius of the cylinder.
    * @param cylinderHeightIn Height for cylinder.
    */
    public Spherocylinder(String labelIn, double radiusIn, double cylinderHeightIn) {
        setLabel(labelIn);
        setRadius(radiusIn);
        setCylinderHeight(cylinderHeightIn);
        count++;
    }

    // Methods
    /**
    * Getter for the label variable of the Spherocylinder.
    * @return The current label(name).
    */
    public String getLabel() {
        return label;
    }

    /**
    * Setter method for the label variable of the Spherocylinder.
    * @param labelIn Taken input for the name of the cylinder.
    * @return True if the label was set and false otherwise.
    */
    public boolean setLabel(String labelIn) {
        boolean isSet = false;
        if (labelIn != null) {
            label = labelIn.trim();
            isSet = true;
        }
        return isSet;
    }

    /**
    * Getter for the radius variable of the Spherocylinder.
    * @return The current radius.
    */
    public double getRadius() {
        return radius;
    }

    /**
    * Setter for the radius variable of the Spherocylinder.
    * @param radiusIn Taken input for the radius of a Spherocylinder.
    * @return True if radius was set and false otherwise.
    */
    public boolean setRadius(double radiusIn) {
        boolean isSet = false;
        if (radiusIn >= 0) { // Validates for non-negative input.
            radius = radiusIn;
            isSet = true;
        }
        return isSet;
    }

    /**
    * Getter for the height variable of the Spherocylinder.
    * @return The current cylinder height for the object.
    */
    public double getCylinderHeight() {
        return cylinderHeight;
    }

    /**
    * Setter for the height variable of the Spherocylinder.
    * @param cylinderHeightIn Taken input for height variable of a Spherocylinder object.
    * @return True if height variable was set and false otherwise.
    */
    public boolean setCylinderHeight(double cylinderHeightIn) {
        boolean isSet = false;
        if (cylinderHeightIn >= 0) { // Validates for non-negative input.
            cylinderHeight = cylinderHeightIn;
            isSet = true;
        }
        return isSet;
    }

    /**
    * Calculates the circumference of the Spherocylinder object.
    * @return The calculated circumference using Formula: 2 * π * radius.
    */
    public double circumference() {
        return 2 * Math.PI * radius;
    }

    /**
    * Calculates the surface area of the Spherocylinder object.
    * @return The calculated surface area using Formula: (2 * radius + cylinderHeight) * (2 * π * radius).
    */
    public double surfaceArea() {
        return (2 * radius + cylinderHeight) * (2 * Math.PI * radius);
    }

    /**
    * Calculates the volume of the Spherocylinder object.
    * @return The calculated volume using Formula: π * radius² * (4/3 * radius + cylinderHeight).
    */
    public double volume() {
        return Math.PI * Math.pow(radius, 2) * ((4.0 / 3) * radius + cylinderHeight);
    }

    /**
    * Provides a string representation of the Spherocylinder object.
    * Includes all variables and calculated properties, formatted for easier viewing.
    * @return A descriptive string representation of the Spherocylinder object.
    */
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.0##");
        String output = "Spherocylinder \"" + label + "\" with radius "
            + radius + " and cylinder height " + cylinderHeight
            + " has:\n\tcircumference = " + df.format(circumference()) + " units\n"
            + "\tsurface area = " + df.format(surfaceArea()) + " square units\n"
            + "\tvolume = " + df.format(volume()) + " cubic units";
        return output;
    }

    /**
    * Getter for the variable count that holds the # of Spherocylinders created.
    * @return The current total number of Spherocylinder objects.
    */
    public static int getCount() {
        return count;
    }

    /**
    * Reset the count variable of Spherocylinders created to zero.
    */
    public static void resetCount() {
        count = 0;
    }

    /**
    * Compares the current Spherocylinder to another object for equality.
    * Two Spherocylinders are equal if their label, radius, and cylinder height match.
    * @param obj The object to compare.
    * @return True if the objects are equal and false otherwise.
    */
    public boolean equals(Object obj) {
        if (!(obj instanceof Spherocylinder)) {
            return false;
        } else {
            Spherocylinder d = (Spherocylinder) obj;
            return (label.equalsIgnoreCase(d.getLabel())
                && Math.abs(radius - d.getRadius()) < 0.000001
                && Math.abs(cylinderHeight - d.getCylinderHeight()) < 0.000001);
        }
    }

    /**
    * Placeholder hashCode method. Used simply in this implementation 
    * to satisfy checksyles since the equals method is defined.
    * @return 0 as the hash code, as required for compatibility with the equals method.
    */
    public int hashCode() {
        return 0;
    }

    /**
    * Compares this Spherocylinder's volume with another defined Spherocylinder object.
    * @param other Is a different Spherocylinder instance being compared to.
    * @return 0 if volumes are equal, -1 if this is smaller, or 1 if this is larger.
    */
    public int compareTo(Spherocylinder other) {
        if (Math.abs(this.volume() - other.volume()) < 0.00001) {
            return 0;
        } else if (this.volume() < other.volume()) {
            return -1;
        } else {
            return 1;
        }
    }
}