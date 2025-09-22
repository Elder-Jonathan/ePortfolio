package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.*;

public class Diagnosis {
    private final IntegerProperty diagnosisId   = new SimpleIntegerProperty();
    private final StringProperty diagnosisName  = new SimpleStringProperty();
    private final StringProperty description    = new SimpleStringProperty();

    public Diagnosis(int diagnosisId, String diagnosisName, String description) {
        this.diagnosisId.set(diagnosisId);
        this.diagnosisName.set(diagnosisName);
        this.description.set(description);
    }

    public int getDiagnosisId()          { return diagnosisId.get(); }
    public String getDiagnosisName()     { return diagnosisName.get(); }
    public String getDescription()       { return description.get(); }

    public void setDiagnosisName(String val) { this.diagnosisName.set(val); }
    public void setDescription(String val)   { this.description.set(val); }

    // ...
}
