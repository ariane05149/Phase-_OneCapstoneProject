package model;

public class GraduateStudent extends Student {
    public GraduateStudent(String name, String email, String id, String dept) {
        super(name, email, id, dept);
    }

    @Override
    public double calculateTuition() {
        return getEnrolledCourses().size() * 5000 + 15000;
    }
}
