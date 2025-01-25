import java.io.FileNotFoundException;

/**
* Intro to Computer Science II - Project 6
* @author Jonathan Elder
* @date   9/24/2022
* This driver class reads cloud storage data from a file and generates reports.
*/

public class CloudStoragePart3 {

    /**
    * Driver program meant to read a file and generate reports based on sorting criteria.
    * @param args Command-line arguments (expects a file name).
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("File name expected as command line argument.");
            System.out.println("Program ending.");
            return;
        }

        String filename = args[0];
        CloudStorageList storageList = new CloudStorageList();

        try {
            storageList.readFile(filename);

            System.out.println(storageList.generateReport());
            System.out.println(storageList.generateReportByName());
            System.out.println(storageList.generateReportByMonthlyCost());
            System.out.println(storageList.generateInvalidRecordsReport());
        } catch (FileNotFoundException e) {
            System.out.println("*** Attempted to read file: " + e.getMessage());
        }
    }
}