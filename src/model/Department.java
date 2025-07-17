package model;

public class Department {
    private int deptId;
    private String deptName;

    public Department() {}
    public Department(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public int getDeptId() { return deptId; }
    public String getDeptName() { return deptName; }

    public void setDeptId(int deptId) { this.deptId = deptId; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    
}
