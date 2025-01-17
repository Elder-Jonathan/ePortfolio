public class SpherocylinderListTest {

    public static void main(String[] args) {
        SpherocylinderList list = new SpherocylinderList("Spherocylinder Test List");

        // Add Spherocylinders to the list
        list.addSpherocylinder("Small Example", 0.5, 0.25);
        list.addSpherocylinder("Medium Example", 10.8, 10.1);
        list.addSpherocylinder("Large Example", 98.32, 99.0);

        // Print the summary
        System.out.println(list);

        // Find a Spherocylinder by label
        System.out.println("\nSearching a Spherocylinder with label: Medium Example..\n"
            + list.findSpherocylinder("Medium Example"));

        // Edit a Spherocylinder
        System.out.println("Editing a Spherocylinder with label: Small Example..");
        if (list.editSpherocylinder("Small Example", 0.6, 0.35)) {
            System.out.println("Match found:\n" + list.findSpherocylinder("Small Example"));
        } else {
            System.out.println("Sorry, no match found!");
        }

        // Delete a Spherocylinder
        System.out.println("Deleting a Spherocylinder with label: Large Example..\n"
            + list.deleteSpherocylinder("Large Example"));

        // Display remaining Spherocylinders
        System.out.println("Displaying all Spherocylinders in the list..");
        for (Spherocylinder c : list.getList()) {
            if (c != null) {
                System.out.println(c);
            }
        }
    }
}