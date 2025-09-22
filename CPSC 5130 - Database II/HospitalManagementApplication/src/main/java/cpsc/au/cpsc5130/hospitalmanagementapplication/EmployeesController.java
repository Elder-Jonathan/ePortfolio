package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

/**
 * This controller interacts with the "employees" table in MySQL:
 *   - Loads all employees at startup (initialize()).
 *   - Add/Update/Delete changes both the DB and the local TableView.
 */
public class EmployeesController {

    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Integer> colEmployeeId;
    @FXML private TableColumn<Employee, String> colFirstName;
    @FXML private TableColumn<Employee, String> colLastName;
    @FXML private TableColumn<Employee, String> colJobCategory;
    @FXML private TableColumn<Employee, String> colDepartment;
    @FXML private TableColumn<Employee, String> colContactInfo;

    @FXML private TextField txtEmployeeId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtContactInfo;

    @FXML private ComboBox<String> comboJobCategory;
    @FXML private ComboBox<String> comboDepartment;

    // Holds our in-memory list of employees
    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("Initializing Employees View (with SQL)...");

        colEmployeeId.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject()
        );
        colFirstName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFirstName())
        );
        colLastName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLastName())
        );
        colJobCategory.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getJobCategory())
        );
        colDepartment.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDepartment())
        );
        colContactInfo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getContactInfo())
        );

        comboJobCategory.setItems(FXCollections.observableArrayList(
                "Doctor", "Nurse", "Technician", "Janitorial", "Administrator"
        ));
        comboDepartment.setItems(FXCollections.observableArrayList(
                "Cardiology", "Pediatrics", "Radiology", "Surgery", "Admissions"
        ));

        loadEmployeesFromDB();

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtEmployeeId.setText(String.valueOf(newSelection.getEmployeeId()));
                txtFirstName.setText(newSelection.getFirstName());
                txtLastName.setText(newSelection.getLastName());
                comboJobCategory.setValue(newSelection.getJobCategory());
                comboDepartment.setValue(newSelection.getDepartment());
                txtContactInfo.setText(newSelection.getContactInfo());
            } else {
                clearForm();
            }
        });

        employeeTable.setOnMouseClicked(event -> {
            if (employeeTable.getSelectionModel().getSelectedItem() == null) {
                clearForm();
            }
        });
    }

    private void clearForm() {
        txtEmployeeId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        comboJobCategory.setValue(null);
        comboDepartment.setValue(null);
        txtContactInfo.clear();
    }

    private void loadEmployeesFromDB() {
        employees.clear();
        String sql = "SELECT * FROM employees";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("employee_id");
                    String firstName = rs.getString("first_name");
                    String lastName  = rs.getString("last_name");
                    String jobCat    = rs.getString("job_category");
                    String dept      = rs.getString("department");
                    String contact   = rs.getString("contact_info");

                    employees.add(new Employee(id, firstName, lastName, jobCat, dept, contact));
                }
            }
            employeeTable.setItems(employees);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading employees from DB: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        String firstName  = txtFirstName.getText();
        String lastName   = txtLastName.getText();
        String jobCat     = comboJobCategory.getValue() != null ? comboJobCategory.getValue() : "";
        String dept       = comboDepartment.getValue() != null ? comboDepartment.getValue() : "";
        String contact    = txtContactInfo.getText();

        String insertSQL = "INSERT INTO employees (first_name, last_name, job_category, department, contact_info) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, jobCat);
                stmt.setString(4, dept);
                stmt.setString(5, contact);
                stmt.executeUpdate();
            }
            // Refresh the employees view after adding a new employee
            loadEmployeesFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding new employee: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No employee selected for update.");
            return;
        }
        int empId = selected.getEmployeeId();

        String firstName  = txtFirstName.getText();
        String lastName   = txtLastName.getText();
        String jobCat     = comboJobCategory.getValue() != null ? comboJobCategory.getValue() : "";
        String dept       = comboDepartment.getValue() != null ? comboDepartment.getValue() : "";
        String contact    = txtContactInfo.getText();

        String updateSQL = "UPDATE employees SET first_name=?, last_name=?, job_category=?, department=?, contact_info=? WHERE employee_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, jobCat);
                stmt.setString(4, dept);
                stmt.setString(5, contact);
                stmt.setInt(6, empId);
                stmt.executeUpdate();
            }
            // Refresh view after update
            loadEmployeesFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating employee: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No employee selected for delete.");
            return;
        }
        int empId = selected.getEmployeeId();

        String deleteSQL = "DELETE FROM employees WHERE employee_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
                stmt.setInt(1, empId);
                stmt.executeUpdate();
            }
            // Refresh view after deletion
            loadEmployeesFromDB();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
}

