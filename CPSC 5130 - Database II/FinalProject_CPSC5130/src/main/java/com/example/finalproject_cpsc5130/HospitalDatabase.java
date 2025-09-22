package com.example.finalproject_cpsc5130;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HospitalDatabase {

    private static Connection connect() throws SQLException {
        // Database connection details
        String servername = "sysmysql8.auburn.edu";
        String username = "jce0039";
        String password = "jce0039dbPASSWORD";
        String dbname = "jce0039db";

        String url = "jdbc:mysql://" + servername + "/" + dbname;
        return DriverManager.getConnection(url, username, password);
    }

    private static String getQueryFromSqlFile(String queryName) throws IOException {
        String entireSqlFileContent = new String(Files.readAllBytes(Paths.get("HospitalDb.sql")));
        Pattern pattern = Pattern.compile("--\\s*" + queryName + "\\s*(.+?)(?=--|$)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(entireSqlFileContent);
        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            throw new IllegalArgumentException("Query named " + queryName + " not found in HospitalDb.sql.");
        }
    }

    private static List<Map<String, Object>> executeQueryWithCustomQuery(String query) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> rowMap = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    Object columnValue = resultSet.getObject(i);
                    rowMap.put(columnName, columnValue);
                }
                resultList.add(rowMap);
            }
        }
        return resultList;
    }

    private static List<Map<String, Object>> executeQuery(String queryName) throws IOException, SQLException {
        String query = getQueryFromSqlFile(queryName);
        return executeQueryWithCustomQuery(query);
    }

    // --- Room Utilization ---

    // GET_OCCUPIED_ROOMS
    public static List<String> getOccupiedRooms() throws IOException {
        List<String> occupiedRooms = new ArrayList<>();
        String query = getQueryFromSqlFile("GET_OCCUPIED_ROOMS");
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                occupiedRooms.add(resultSet.getString("room_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return occupiedRooms;
    }

    // GET_UNOCCUPIED_ROOMS
    public static List<String> getUnoccupiedRooms() throws IOException {
        List<String> unoccupiedRooms = new ArrayList<>();
        String query = getQueryFromSqlFile("GET_UNOCCUPIED_ROOMS");
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                unoccupiedRooms.add(resultSet.getString("room_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unoccupiedRooms;
    }

    // GET_ALL_ROOMS – now returns maps with keys "Room Number" and "Status"
    public static List<Map<String, Object>> getAllRooms() throws IOException {
        List<Map<String, Object>> allRooms = new ArrayList<>();
        String query = getQueryFromSqlFile("GET_ALL_ROOMS");
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Map<String, Object> roomMap = new LinkedHashMap<>();
                roomMap.put("Room Number", resultSet.getString("Room Number"));
                roomMap.put("Status", resultSet.getString("Status"));
                allRooms.add(roomMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allRooms;
    }

    // --- Patient Information ---

    public static List<Map<String, Object>> getAllPatients() throws IOException, SQLException {
        return executeQuery("LIST_ALL_PATIENTS");
    }

    public static List<Map<String, Object>> getCurrentPatients() throws IOException, SQLException {
        return executeQuery("LIST_CURRENT_PATIENTS");
    }

    public static List<Map<String, Object>> getDischargedPatients(String startDate, String endDate) throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_DISCHARGED_PATIENTS")
                .replace("'2023-06-01'", "'" + startDate + "'")
                .replace("'2023-06-15'", "'" + endDate + "'");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getAdmittedPatients(String startDate, String endDate) throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_ADMITTED_PATIENTS")
                .replace("'2023-07-15'", "'" + startDate + "'")
                .replace("'2023-08-01'", "'" + endDate + "'");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getAdmissionsByPatientName(String firstName, String lastName) throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_ADMISSIONS_BY_PATIENT_NAME")
                .replace("'Jonathan'", "'" + firstName + "'")
                .replace("'Elder'", "'" + lastName + "'");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getTreatmentsByAdmissionsForPatient(int patientId, String firstName, String lastName) throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_TREATMENTS_BY_ADMISSIONS_FOR_PATIENT")
                .replace("0001", String.valueOf(patientId))
                .replace("'Jonathan'", "'" + firstName + "'")
                .replace("'Elder'", "'" + lastName + "'");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getPatientsReadmittedWithin30Days() throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_PATIENTS_READMITTED_WITHIN_30_DAYS");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getPatientAdmissionStatistics() throws IOException, SQLException {
        return executeQuery("PATIENT_ADMISSION_STATISTICS");
    }

    // --- Diagnosis and Treatment Information ---

    public static List<Map<String, Object>> getDiagnosesByOccurrences() throws IOException, SQLException {
        return executeQuery("LIST_DIAGNOSES_BY_OCCURRENCES");
    }

    public static List<Map<String, Object>> getTreatmentOccurrenceStats() throws IOException, SQLException {
        return executeQuery("TREATMENT_OCCURRENCE_STATS");
    }

    public static List<Map<String, Object>> getFrequentPatientDiagnosisCorrelation() throws IOException, SQLException {
        return executeQuery("FREQUENT_PATIENT_DIAGNOSIS_CORRELATION");
    }

    // Returns a single record (wrapped as a map) for treatment orderer and patient info.
    public static Map<String, Object> getTreatmentOrdererAndPatientInfo(int patientTreatmentId) throws IOException, SQLException {
        String query = getQueryFromSqlFile("TREATMENT_ORDERER_AND_PATIENT_INFO").replace("[PatientTreatmentID]", String.valueOf(patientTreatmentId));
        List<Map<String, Object>> results = executeQueryWithCustomQuery(query);
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    // --- Employee Information ---

    public static List<Map<String, Object>> getAllEmployees() throws IOException, SQLException {
        return executeQuery("LIST_ALL_EMPLOYEES");
    }

    public static List<Map<String, Object>> getDoctorsWithHighAdmissionRatePatients() throws IOException, SQLException {
        return executeQuery("DOCTORS_WITH_HIGH_ADMISSION_RATE_PATIENTS");
    }

    public static List<Map<String, Object>> getDoctorSpecificDiagnosisStats(int givenDoctorId) throws IOException, SQLException {
        String query = getQueryFromSqlFile("DOCTOR_SPECIFIC_DIAGNOSIS_STATS").replace("[GivenDoctorID]", String.valueOf(givenDoctorId));
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getDoctorSpecificTreatmentStats(int givenDoctorId) throws IOException, SQLException {
        String query = getQueryFromSqlFile("DOCTOR_SPECIFIC_TREATMENT_STATS").replace("[GivenDoctorID]", String.valueOf(givenDoctorId));
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getEmployeesTreatingAllPatients() throws IOException, SQLException {
        return executeQuery("EMPLOYEES_TREATING_ALL_PATIENTS");
    }

    // --- New Methods for Missing Functionality ---

    public static List<Map<String, Object>> getSearchPatientDetails(String searchTerm) throws IOException, SQLException {
        String query = getQueryFromSqlFile("SEARCH_PATIENT_DETAILS").replace("[SEARCHTERM]", searchTerm);
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getAllDoctors() throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_ALL_DOCTORS");
        return executeQueryWithCustomQuery(query);
    }

    public static List<Map<String, Object>> getDoctorsWithCurrentPatients() throws IOException, SQLException {
        String query = getQueryFromSqlFile("LIST_DOCTORS_WITH_CURRENT_PATIENTS");
        return executeQueryWithCustomQuery(query);
    }
}

