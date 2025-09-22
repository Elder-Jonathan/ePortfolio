package cpsc.au.cpsc5130.hospitalmanagementapplication;

import java.sql.Date;
import javafx.beans.property.*;

public class Admission {
    private final IntegerProperty admissionId   = new SimpleIntegerProperty();
    private final IntegerProperty patientId     = new SimpleIntegerProperty();
    private final IntegerProperty roomNumber    = new SimpleIntegerProperty();
    private final ObjectProperty<Date> admissionDate = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> dischargeDate = new SimpleObjectProperty<>();
    private final IntegerProperty primaryDoctorId    = new SimpleIntegerProperty();
    private final StringProperty diagnosis     = new SimpleStringProperty();

    public Admission(int admissionId, int patientId, int roomNumber,
                     Date admissionDate, Date dischargeDate,
                     int primaryDoctorId, String diagnosis) {
        this.admissionId.set(admissionId);
        this.patientId.set(patientId);
        this.roomNumber.set(roomNumber);
        this.admissionDate.set(admissionDate);
        this.dischargeDate.set(dischargeDate);
        this.primaryDoctorId.set(primaryDoctorId);
        this.diagnosis.set(diagnosis);
    }

    // Getters
    public int getAdmissionId()        { return admissionId.get(); }
    public int getPatientId()          { return patientId.get(); }
    public int getRoomNumber()         { return roomNumber.get(); }
    public Date getAdmissionDate()     { return admissionDate.get(); }
    public Date getDischargeDate()     { return dischargeDate.get(); }
    public int getPrimaryDoctorId()    { return primaryDoctorId.get(); }
    public String getDiagnosis()       { return diagnosis.get(); }

    // Setters
    public void setPatientId(int val)          { this.patientId.set(val); }
    public void setRoomNumber(int val)         { this.roomNumber.set(val); }
    public void setAdmissionDate(Date val)     { this.admissionDate.set(val); }
    public void setDischargeDate(Date val)     { this.dischargeDate.set(val); }
    public void setPrimaryDoctorId(int val)    { this.primaryDoctorId.set(val); }
    public void setDiagnosis(String val)       { this.diagnosis.set(val); }
    public String getAdmissionCode() {
        return "ADM" + String.format("%05d", getAdmissionId());
    }

    // Property methods if needed
    // ...
}
