package dao;

import db.DBConnection;
import java.sql.*;

public class DepartmentDAO {

    public void displayAllDepartments() {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT dept_id, dept_name FROM department";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nAvailable Departments:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("dept_id") +
                        " | Name: " + rs.getString("dept_name"));
            }

        } catch (Exception e) {
            System.out.println("Error fetching departments: " + e.getMessage());
        }
    }

    public int getDeptIdByName(String deptName) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT dept_id FROM department WHERE dept_name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, deptName);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt("dept_id");
        } catch (Exception e) {
            System.out.println("Error fetching department: " + e.getMessage());
        }
        return -1;
    }

    public void addDepartment(String deptName) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO department (dept_name) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, deptName);
            ps.executeUpdate();
            System.out.println("Department added.");
        } catch (Exception e) {
            System.out.println("Error adding department: " + e.getMessage());
        }
    }

}
