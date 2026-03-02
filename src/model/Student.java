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

    public void setGPA(double gpa) {
        this.GPA = gpa;
    }

    public Map<Course, Double> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollCourse(Course c, double grade) {
        enrolledCourses.put(c, grade);
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
                " | GPA: " + GPA +
                " | Tuition: " + calculateTuition();
    }
}