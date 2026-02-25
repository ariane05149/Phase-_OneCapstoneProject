package model;

public class Instructor extends Person {
    private String department;

    public Instructor(String name, String email, String dept) {
        super(name, email);
        this.department = dept;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }
    public String getDepartment() {
        return department;
    }
}