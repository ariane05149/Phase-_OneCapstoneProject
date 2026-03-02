package model;

public class GraduateStudent extends Student {

    private static final double PER_CREDIT = 5000;
    private static final double RESEARCH_FEE = 2000;

    public GraduateStudent(String name, String email, String id, String dept) {
        super(name, email, id, dept);
    }

    @Override
    public double calculateTuition() {

        int totalCredits = getEnrolledCourses().keySet()
                .stream()
                .mapToInt(Course::getCredits)
                .sum();

        return (totalCredits * PER_CREDIT) + RESEARCH_FEE;
    }

    @Override
    public String getRole() {
        return "Graduate";
    }
}