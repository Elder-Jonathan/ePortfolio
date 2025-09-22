Hospital Database System - README.md 
1. This in the Checkpoint 4 assignment for Jonathan Elder for CPSC 5130 and how to setup the application using Intellij and maven framework.
2. You will need these files located on the assignment page: Hospital-view.fxml, HospitalDb.sql, HospitalDatabase.java, HospitalController.java, and HospitalApplication.java. 
3. There are also updated versions of the module-info.java and pom.xml files that update for the proper dependencies.
4. In Intellij I have a package named edu.au.cpsc.FinalProjectCPSC5130. You will need to either rename the package or make the same package extention for the file to be located properly.
5. This also means that my resources have a location of edu.au.cpsc.FinalProjectCPSC5130. Again you can make the structure how you like but the files will not be locatable unless the correct package is used.
6. The FXML file/java program was constructed in a way to have a label and button for each query needed in each section.
7. The resultTable is then dynamically loaded into the tableView by first clearing the contents of the resultTable and then dynamically loading the column names needed for each query to perform.
8. The controller then links "onAction" events to the button click and then uses the various methods located inside HospitalDatabase.java to then search through HospitalDb.sql for the proper query needed in the method.

