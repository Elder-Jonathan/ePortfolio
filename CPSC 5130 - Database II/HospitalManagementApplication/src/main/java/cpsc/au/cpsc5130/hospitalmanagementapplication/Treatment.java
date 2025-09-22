package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.*;

public class Treatment {
    private final IntegerProperty treatmentId = new SimpleIntegerProperty();
    private final StringProperty name        = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();

    public Treatment(int treatmentId, String name, String description) {
        this.treatmentId.set(treatmentId);
        this.name.set(name);
        this.description.set(description);
    }

    public int getTreatmentId()    { return treatmentId.get(); }
    public String getName()        { return name.get(); }
    public String getDescription() { return description.get(); }

    public void setName(String val)        { this.name.set(val); }
    public void setDescription(String val) { this.description.set(val); }
}