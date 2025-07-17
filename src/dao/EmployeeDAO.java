package dao;

import db.DBConnection;
import model.Employee;

import java.sql.*;
import java.util.*;

public class EmployeeDAO {

    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employee (name, salary, dept_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDeptId());
            ps.executeUpdate();
            System.out.println("Employee added.");
        } catch (Exception e) {
            System.out.println("Add failed: " + e.getMessage());
        }
    }

    public void updateEmployee(int id, Scanner sc) {
        System.out.println("Update: 1.Name  2.Department  3.Salary  4.All");
        int choice = sc.nextInt();
        sc.nextLine();
        String sql = "";
        try (Connection con = DBConnection.getConnection()) {
            switch (choice) {
                case 1:
                    System.out.print("Enter new name: ");
                    String name = sc.nextLine();
                    sql = "UPDATE employee SET name=? WHERE emp_id=?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                    }
                    break;
                case 2:
                    System.out.print("Enter new dept_id: ");
                    int deptId = sc.nextInt();
                    sql = "UPDATE employee SET dept_id=? WHERE emp_id=?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setInt(1, deptId);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                    }
                    break;
                case 3:
                    System.out.print("Enter new salary: ");
                    double sal = sc.nextDouble();
                    sql = "UPDATE employee SET salary=? WHERE emp_id=?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setDouble(1, sal);
                        ps.setInt(2, id);
                        ps.executeUpdate();
                    }
                    break;
                case 4:
                    System.out.print("Enter new name: ");
                    name = sc.nextLine();
                    System.out.print("Enter new dept_id: ");
                    deptId = sc.nextInt();
                    System.out.print("Enter new salary: ");
                    sal = sc.nextDouble();
                    sql = "UPDATE employee SET name=?, dept_id=?, salary=? WHERE emp_id=?";
                    try (PreparedStatement ps = con.prepareStatement(sql)) {
                        ps.setString(1, name);
                        ps.setInt(2, deptId);
                        ps.setDouble(3, sal);
                        ps.setInt(4, id);
                        ps.executeUpdate();
                    }
                    break;
            }
            System.out.println("Update successful.");
        } catch (Exception e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM employee WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Employee deleted.");
        } catch (Exception e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    // Procedure to display all employee
    // DELIMITER $$

    // CREATE PROCEDURE getAllEmployees()
    // BEGIN
    // SELECT e.emp_id, e.name, e.salary, d.dept_name
    // FROM employee e
    // LEFT JOIN department d ON e.dept_id = d.dept_id;
    // END$$

    // DELIMITER ;

    public void displayAll() {
        try (Connection con = DBConnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL getAllEmployees()}");
            ResultSet rs = cs.executeQuery();

            System.out.printf("%-5s %-20s %-10s %-15s\n", "ID", "Name", "Salary", "Department");
            System.out.println("---------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-10.2f %-15s\n",
                        rs.getInt("emp_id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("dept_name"));
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Display error: " + e.getMessage());
        }
    }

    // procedure to get department wise average salary
    // DELIMITER $$

    // CREATE PROCEDURE getDeptWiseAvgSalary()
    // BEGIN
    // SELECT d.dept_name, AVG(e.salary) AS avg_salary
    // FROM department d
    // LEFT JOIN employee e ON d.dept_id = e.dept_id
    // GROUP BY d.dept_name;
    // END$$

    // DELIMITER ;

    public void displayDeptWiseAvgSalary() {
        try (Connection con = DBConnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL getDeptWiseAvgSalary()}");
            ResultSet rs = cs.executeQuery();

            System.out.printf("%-20s %-15s\n", "Department", "Avg Salary");
            System.out.println("------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-20s %-15.2f\n",
                        rs.getString("dept_name"),
                        rs.getDouble("avg_salary"));
            }

            cs.close();
        } catch (Exception e) {
            System.out.println("Avg salary error: " + e.getMessage());
        }
    }

    // DELIMITER $$

    // CREATE PROCEDURE getDeptWiseMaxSalaryWithEmp()
    // BEGIN
    // SELECT d.dept_name, e.name AS emp_name, e.salary
    // FROM employee e
    // INNER JOIN department d ON e.dept_id = d.dept_id
    // WHERE (e.dept_id, e.salary) IN (
    // SELECT e2.dept_id, MAX(e2.salary)
    // FROM employee e2
    // GROUP BY e2.dept_id
    // );
    // END$$

    // DELIMITER ;

    public void displayDeptWiseMaxSalaryWithEmployee() {
        try (Connection con = DBConnection.getConnection()) {
            CallableStatement cs = con.prepareCall("{CALL getDeptWiseMaxSalaryWithEmp()}");
            ResultSet rs = cs.executeQuery();

            System.out.printf("%-20s %-20s %-15s\n", "Department", "Employee", "Max Salary");
            System.out.println("--------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-20s %-20s %-15.2f\n",
                        rs.getString("dept_name"),
                        rs.getString("emp_name"),
                        rs.getDouble("salary"));
            }
            cs.close();
        } catch (Exception e) {
            System.out.println("Max salary with employee error: " + e.getMessage());
        }
    }

}
