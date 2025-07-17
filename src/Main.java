import dao.*;
import model.Employee;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDAO ddao = new DepartmentDAO();
        EmployeeDAO edao = new EmployeeDAO();

        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Department");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Department Salary Stats");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter department name: ");
                    String dname = sc.nextLine();
                    ddao.addDepartment(dname);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter salary: ");
                    double sal = sc.nextDouble();
                    sc.nextLine();

                    // Show departments before asking user
                    ddao.displayAllDepartments();
                    System.out.print("Enter department name: ");
                    String deptName = sc.nextLine();
                    int deptId = ddao.getDeptIdByName(deptName);

                    if (deptId == -1) {
                        System.out.println("Department not found. Add it first.");
                    } else {
                        edao.addEmployee(new Employee(name, sal, deptId));
                    }
                    break;
                case 3:
                    System.out.print("Enter employee ID to update: ");
                    int uid = sc.nextInt();
                    edao.updateEmployee(uid, sc);
                    break;
                case 4:
                    System.out.print("Enter employee ID to delete: ");
                    int did = sc.nextInt();
                    edao.deleteEmployee(did);
                    break;
                case 5:
                    edao.displayAll();
                    break;
                case 6:
                    System.out.println("Department wise average salary of an employee : ");
                    edao.displayDeptWiseAvgSalary();
                    System.out.println();
                    System.out.println("Department wise employee with highest salary : ");
                    edao.displayDeptWiseMaxSalaryWithEmployee();
                    break;
                case 7:
                    System.out.println("Exiting.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
