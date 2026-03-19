package service;

import model.*;
import exception.*;

import java.util.HashMap;
import java.util.Map;

public class UniversityManager {

    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();


    public void registerStudent(Student s) throws StudentAlreadyEnrolledException {

        if (students.containsKey(s.getStudentID()))
            throw new StudentAlreadyEnrolledException("Student with this ID already exists.");

        students.put(s.getStudentID(), s);
    }

    public void createCourse(Course c) throws StudentAlreadyEnrolledException {

        if (courses.containsKey(c.getCode()))
            throw new StudentAlreadyEnrolledException("Course with this code already exists.");

        courses.put(c.getCode(), c);
    }


    public void enrollStudentInCourse(String studentId, String courseCode, double grade)
            throws CourseFullException, StudentAlreadyEnrolledException {

        Student s = students.get(studentId);
        Course c = courses.get(courseCode);

        if (s == null)
            throw new IllegalArgumentException("Student not found.");

        if (c == null)
            throw new IllegalArgumentException("Course not found.");

        if (c.isFull())
            throw new CourseFullException("Course is full.");

        if (s.getEnrolledCourses().containsKey(c))
            throw new StudentAlreadyEnrolledException("Student already enrolled in this course.");

        s.enrollCourse(c, grade);
        c.addStudent(s);



    }

    // gpa averg calculet

    public double calculateAverageGPA(String dept) {

        return students.values().stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(dept))
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

    public Student getTopStudent() {

        return students.values().stream()
                .max((s1, s2) -> Double.compare(s1.getGPA(), s2.getGPA()))
                .orElse(null);
    }


    public Student getStudent(String id) {
        return students.get(id);
    }

    public Map<String, Student> getAllStudents() {
        return students;
    }

    public Map<String, Course> getAllCourses() {
        return courses;
    }
  //  load files

    public void addLoadedStudent(Student s) {
        students.put(s.getStudentID(), s);
    }

    public void addLoadedCourse(Course c) {
        courses.put(c.getCode(), c);
    }
}