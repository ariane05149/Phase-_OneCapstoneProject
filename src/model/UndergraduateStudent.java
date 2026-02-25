package model;


public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String name, String email, String id, String dept) {
        super(name, email, id, dept);
    }

    @Override
    public double calculateTuition() {
        return 5000;
    }
}