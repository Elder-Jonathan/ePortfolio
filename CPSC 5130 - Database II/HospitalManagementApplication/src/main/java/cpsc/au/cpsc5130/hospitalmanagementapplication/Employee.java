package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.*;

public class Employee {
    private final IntegerProperty employeeId = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final StringProperty jobCategory = new SimpleStringProperty();
    private final StringProperty department = new SimpleStringProperty();
    private final StringProperty contactInfo = new SimpleStringProperty();

    private static final int EMPLOYEE_OFFSET = 10000;

    public Employee(int employeeId, String firstName, String lastName,
                    String jobCategory, String department, String contactInfo) {
        this.employeeId.set(employeeId);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.jobCategory.set(jobCategory);
        this.department.set(department);
        this.contactInfo.set(contactInfo);
    }

    // Getters
    public int getEmployeeId() {
        return employeeId.get();
    }
    public String getFirstName() {
        return firstName.get();
    }
    public String getLastName() {
        return lastName.get();
    }
    public String getJobCategory() {
        return jobCategory.get();
    }
    public String getDepartment() {
        return department.get();
    }
    public String getContactInfo() {
        return contactInfo.get();
    }

    // Setters (used by the EmployeesController for updating existing employees)
    public void setFirstName(String value) {
        this.firstName.set(value);
    }
    public void setLastName(String value) {
        this.lastName.set(value);
    }
    public void setJobCategory(String value) {
        this.jobCategory.set(value);
    }
    public void setDepartment(String value) {
        this.department.set(value);
    }
    public void setContactInfo(String value) {
        this.contactInfo.set(value);
    }

    // (Optional) Expose properties if you want to bind them directly to controls
    public IntegerProperty employeeIdProperty() {
        return employeeId;
    }
    public StringProperty firstNameProperty() {
        return firstName;
    }
    public StringProperty lastNameProperty() {
        return lastName;
    }
    public StringProperty jobCategoryProperty() {
        return jobCategory;
    }
    public StringProperty departmentProperty() {
        return department;
    }
    public StringProperty contactInfoProperty() {
        return contactInfo;
    }

    // Unique code formatting: "EMP00001" or something similar
// The "display ID" is simply EMPLOYEE_OFFSET + the actual auto-increment
    public int getDisplayId() {
        return EMPLOYEE_OFFSET + getEmployeeId();
    }
}
