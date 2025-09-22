package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.*;

/**
 * Model class for the "patients" table in MySQL:
 *   patient_id, first_name, last_name, emergency_contact,
 *   insurance_info, diagnosis_history
 */
public class Patient {
    private final IntegerProperty patientId = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty emergencyContact = new SimpleStringProperty();
    private final StringProperty insuranceInfo = new SimpleStringProperty();
    private final StringProperty diagnosisHistory = new SimpleStringProperty();

    // Choose a different offset for patients, e.g. 20000
    private static final int PATIENT_OFFSET = 20000;

    public Patient(int patientId, String firstName, String lastName,
                   String emergencyContact, String insuranceInfo, String diagnosisHistory) {
        this.patientId.set(patientId);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.emergencyContact.set(emergencyContact);
        this.insuranceInfo.set(insuranceInfo);
        this.diagnosisHistory.set(diagnosisHistory);
    }

    // Getters
    public int getPatientId()               { return patientId.get(); }
    public String getFirstName()            { return firstName.get(); }
    public String getLastName()             { return lastName.get(); }
    public String getEmergencyContact()     { return emergencyContact.get(); }
    public String getInsuranceInfo()        { return insuranceInfo.get(); }
    public String getDiagnosisHistory()     { return diagnosisHistory.get(); }

    // Setters
    public void setFirstName(String val)            { this.firstName.set(val); }
    public void setLastName(String val)             { this.lastName.set(val); }
    public void setEmergencyContact(String val)     { this.emergencyContact.set(val); }
    public void setInsuranceInfo(String val)        { this.insuranceInfo.set(val); }
    public void setDiagnosisHistory(String val)     { this.diagnosisHistory.set(val); }

    // (Optional) Property getters if you want direct binding
    public IntegerProperty patientIdProperty()              { return patientId; }
    public StringProperty  firstNameProperty()              { return firstName; }
    public StringProperty  lastNameProperty()               { return lastName; }
    public StringProperty  emergencyContactProperty()       { return emergencyContact; }
    public StringProperty  insuranceInfoProperty()          { return insuranceInfo; }
    public StringProperty  diagnosisHistoryProperty()       { return diagnosisHistory; }

    public String getPatientCode() {
        return "PAT" + String.format("%05d", getPatientId());
    }
}
