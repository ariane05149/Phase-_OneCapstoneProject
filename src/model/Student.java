package model;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {

    private String studentID;
    private String department;
    private double GPA;
    private Map<Course, Double> enrolledCourses;

    public Student(String name, String email, String studentID, String department) {
        super(name, email);
        this.studentID = studentID;
        this.department = department;
        this.GPA = 0.0;
        this.enrolledCourses = new HashMap<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getDepartment() {
        return department;
    }

    public double getGPA() {
        return GPA;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    // 🔥 Enrollment now recalculates GPA automatically
    public void enrollCourse(Course c, double grade) {
        enrolledCourses.put(c, grade);
        calculateGPA();  // VERY IMPORTANT
    }

    // 🔥 REAL CREDIT-WEIGHTED GPA CALCULATION
    private void calculateGPA() {

        double totalQualityPoints = 0;
        int totalCredits = 0;

        for (Map.Entry<Course, Double> entry : enrolledCourses.entrySet()) {

            Course course = entry.getKey();
            double percentage = entry.getValue();

            double gradePoint = convertToGradePoint(percentage);

            totalQualityPoints += gradePoint * course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits > 0) {
            this.GPA = totalQualityPoints / totalCredits;
        } else {
            this.GPA = 0.0;
        }
    }

    // 🔥 Convert percentage to 4.0 scale
    private double convertToGradePoint(double percentage) {

        if (percentage >= 90) return 4.0;
        else if (percentage >= 80) return 3.7;
        else if (percentage >= 70) return 3.0;
        else if (percentage >= 60) return 2.0;
        else if (percentage >= 50) return 1.0;
        else return 0.0;
    }

    public double calculateTuition() {
        return 0;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "ID: " + studentID +
                " | Name: " + getName() +
                " | Dept: " + department +
                " | GPA: " + String.format("%.2f", GPA) +
                " | Tuition: " + calculateTuition();
    }
}