import java.io.FileNotFoundException;

/**
* Intro to Computer Science 1220 II - Project 5
* @author Jonathan Elder
* @date   9/20/2022
* This driver class reads cloud storage data from a file and generates reports.
*/
public class CloudStoragePart2 {

    /**
    * Driver program meant to read a file and generate reports based on sorting criteria.
    * @param args Command-line arguments (expects a file name).
    * @throws FileNotFoundException if the specified file is not found.
    */
    public static void main(String[] args) throws FileNotFoundException {
      
        // Checks if a file name was provided in command-line arguments
        if (args.length > 0) {
            String fileNameIn = args[0];

            // Creates an instance of CloudStorageList
            CloudStorageList listObject = new CloudStorageList();

            // Uses the readFile method of the args input to populate the CloudStorageList.
            listObject.readFile(fileNameIn);

            // Generate reports with simple names for reference.
            String generalReport = listObject.generateReport();
            String byNameReport = listObject.generateReportByName();
            String byMonthlyCost = listObject.generateReportByMonthlyCost();

            // Print each report.
            System.out.println(generalReport);
            System.out.println(byNameReport);
            System.out.println(byMonthlyCost);
        } else {
            // Default error message if no file name is provided or found as an args.
            System.out.println("File name expected as command line argument.");
            System.out.println("Program ending.");
        }
    }
}