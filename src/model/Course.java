package model;
import java.util.ArrayList;
import java.util.List;
public class Course {
    private String code;
    private String title;
    private int maxCapacity;
    private List<Student> students;

    public Course(String code, String title, int maxCapacity) {
        this.code = code;
        this.title = title;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }
    public String getTitle() {
        return title;
    }
    public int getCapacity() {
        return maxCapacity;
    }
    public List<Student> getStudents() {
        return students;
    }
    public boolean isFull() {
        return students.size() >= maxCapacity;
    }
    public void addStudent(Student s) {
        students.add(s);
    }
}