package model;

public class Instructor extends Person {

    private String department;

    public Instructor(String name, String email, String id, String department) {
        super(name, email, id);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

}
