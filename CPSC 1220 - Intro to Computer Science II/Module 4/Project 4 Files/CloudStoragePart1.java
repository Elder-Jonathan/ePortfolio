/**
* Intro to Computer Science 1220 II - Project 4
* @author Jonathan Elder
* @date   9/17/2022
*/
public class CloudStoragePart1 {

    /**
    * Contains main method that serves as the driver program to test each class.
    * @param args Creates instances of cloud storage types and prints their details.
    */
    public static void main(String[] args) {
        
        // Calls the constructor of each cloud storage type and creates objects.
        DedicatedCloud c1 = new DedicatedCloud("Cloud One", 40.00, 10.00);
        SharedCloud c2 = new SharedCloud("Cloud Two", 9.00, 12.0, 20.0);
        SharedCloud c3 = new SharedCloud("Cloud Three", 9.00, 25.0, 20.0);
        PublicCloud c4 = new PublicCloud("Cloud Four", 9.00, 25.0, 20.0);
        PersonalCloud c5 = new PersonalCloud("Cloud Five", 9.00, 21.0, 20.0);

        // Prints the formatted toString of each cloud storage object.
        System.out.println("\n" + c1);
        System.out.println("\n" + c2);
        System.out.println("\n" + c3);
        System.out.println("\n" + c4);
        System.out.println("\n" + c5);
    }
}