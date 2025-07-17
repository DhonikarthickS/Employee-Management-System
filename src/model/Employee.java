package model;

public class Employee {
    private int empId;
    private String name;
    private double salary;
    private int deptId;

    public Employee() {
    }

    public Employee(int empId, String name, double salary, int deptId) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Employee(String name, double salary, int deptId) {
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
