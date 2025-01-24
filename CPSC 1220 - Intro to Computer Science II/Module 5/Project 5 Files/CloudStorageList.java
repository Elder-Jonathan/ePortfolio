import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
* Intro to Computer Science II - Project 5
* @author Jonathan Elder
* @date   9/21/2022
* Provides methods for controlling arrays of CloudStorage objects for 
* adding objects, reading from files, and to generate reports.
*/
class CloudStorageList {

    // Instance variables.
    private CloudStorage[] cList;
    private String[] invalidRecords;

    // Constants that abstractly link categories to CloudStorage object types.
    static final char D = 'D'; // Dedicated Cloud
    static final char C = 'C'; // Public Cloud
    static final char S = 'S'; // Shared Cloud
    static final char P = 'P'; // Personal Cloud

    /**
    * Default constructor that initializes the CloudStorage object
    * array and invalid records array with size 0.
    */
    public CloudStorageList() {
        cList = new CloudStorage[0];
        invalidRecords = new String[0];
    }

    /**
    * Getter for the array of CloudStorage objects created after reading in a given file.
    * @return The array containing all CloudStorage objects.
    */
    public CloudStorage[] getCloudStorageArray() {
        return cList;
    }
    
    /**
    * Adds a CloudStorage object to the storage array.
    * Expands the array to accommodate the new object.
    * @param cloudObj The CloudStorage object to be added.
    */
    public void addCloudStorage(CloudStorage cloudObj) {
        cList = Arrays.copyOf(cList, cList.length + 1);
        cList[cList.length - 1] = cloudObj;
    }

    /**
    * Getter for the array of invalid records.
    * @return The array containing invalid records from file processing.
    */
    public String[] getInvalidRecordsArray() {
        return invalidRecords;
    }

    /**
    * Adds an invalid record to the invalid records array.
    * Expands the array by 1 to account for new record.
    * @param line The invalid record to be added.
    */
    public void addInvalidRecord(String line) {
        invalidRecords = Arrays.copyOf(invalidRecords, invalidRecords.length + 1);
        invalidRecords[invalidRecords.length - 1] = line;
    }

    /**
    * Reads cloud storage data from a specified file.
    * Processes each line and creates appropriate CloudStorage objects based on types.
    * @param fileName The name of the input data file.
    * @throws FileNotFoundException Default output for when a specified file cannot be found.
    */
    public void readFile(String fileName) throws FileNotFoundException {
    
        Scanner fileScanner = new Scanner(new File(fileName));
        String line = "";

        while (fileScanner.hasNext()) {
            try {
            
                CloudStorage cloudObj;
                line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                Character category = Character.toUpperCase(lineScanner.next().charAt(0));
                String name = lineScanner.next();
                double baseStorageCost = Double.parseDouble(lineScanner.next());

                switch (category) {
                    case D: // Dedicated Cloud
                        double serverCost = Double.parseDouble(lineScanner.next());
                        cloudObj = new DedicatedCloud(name, baseStorageCost, serverCost);
                        addCloudStorage(cloudObj);
                        break;

                    case C: // Public Cloud
                        double dataStored = Double.parseDouble(lineScanner.next());
                        double dataLimit = Double.parseDouble(lineScanner.next());
                        cloudObj = new PublicCloud(name, baseStorageCost, dataStored, dataLimit);
                        addCloudStorage(cloudObj);
                        break;

                    case S: // Shared Cloud
                        dataStored = Double.parseDouble(lineScanner.next());
                        dataLimit = Double.parseDouble(lineScanner.next());
                        cloudObj = new SharedCloud(name, baseStorageCost, dataStored, dataLimit);
                        addCloudStorage(cloudObj);
                        break;

                    case P: // Personal Cloud
                        dataStored = Double.parseDouble(lineScanner.next());
                        dataLimit = Double.parseDouble(lineScanner.next());
                        cloudObj = new PersonalCloud(name, baseStorageCost, dataStored, dataLimit);
                        addCloudStorage(cloudObj);
                        break;

                    default:
                        throw new InvalidCategoryException("Invalid category: " + category);
                }
            } catch (NumberFormatException | InvalidCategoryException | NoSuchElementException e) {
                line += "\n" + e.getMessage();
                addInvalidRecord(line);
            }
        }
    }

    /**
    * Generates a report of CloudStorage objects in the order read from the file.
    * @return A formatted string containing a report of all CloudStorage objects.
    */
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("-------------------------------\n")
              .append("Monthly Cloud Storage Report\n")
              .append("-------------------------------");
        for (CloudStorage cloudObj : cList) {
            report.append("\n").append(cloudObj).append("\n");
        }
        return report.toString();
    }

    /**
    * Generates a report sorted by the names of CloudStorage objects.
    * @return A formatted string containing CloudStorage object in a report sorted by name.
    */
    public String generateReportByName() {
        StringBuilder report = new StringBuilder();
        report.append("\n-----------------------------------------\n")
              .append("Monthly Cloud Storage Report (by Name)\n")
              .append("-----------------------------------------");
        Arrays.sort(getCloudStorageArray());
        for (CloudStorage cloudObj : cList) {
            report.append("\n").append(cloudObj).append("\n");
        }
        return report.toString();
    }

    /**
    * Generates a report sorted by the monthly cost of CloudStorage objects.
    * @return A formatted string containing CloudStorage object in a report sorted by monthly cost.
    */
    public String generateReportByMonthlyCost() {
        StringBuilder report = new StringBuilder();
        report.append("\n-------------------------------------------------\n")
              .append("Monthly Cloud Storage Report (by Monthly Cost)\n")
              .append("-------------------------------------------------");
        Arrays.sort(getCloudStorageArray(), new MonthlyCostComparator());
        for (CloudStorage cloudObj : cList) {
            report.append("\n").append(cloudObj).append("\n");
        }
        return report.toString();
    }
}