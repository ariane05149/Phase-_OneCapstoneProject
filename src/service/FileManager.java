package service;

import model.*;
import service. UniversityManager.*;

import java.io.*;

public class FileManager {

    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";


    public void saveData(UniversityManager manager) {

        try (PrintWriter sw = new PrintWriter(new FileWriter(STUDENT_FILE));
             PrintWriter cw = new PrintWriter(new FileWriter(COURSE_FILE))) {

            // Save Students
            for (Student s : manager.getAllStudents().values()) {

                sw.println(
                        s.getStudentID() + "," +
                                s.getName() + "," +
                                s.getEmail() + "," +
                                s.getDepartment() + "," +
                                s.getGPA() + "," +
                                s.getRole()
                );
            }


            for (Course c : manager.getAllCourses().values()) {

                cw.println(
                        c.getCode() + "," +
                                c.getTitle() + "," +
                                c.getCapacity() + "," +
                                c.getCredits()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }



    public void loadData(UniversityManager manager) {

        try (BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                String id = p[0];
                String name = p[1];
                String email = p[2];
                String dept = p[3];
                double gpa = Double.parseDouble(p[4]);
                String role = p[5];

                Student s;

                if (role.equalsIgnoreCase("Graduate")) {
                    s = new GraduateStudent(name, email, id, dept);
                } else {
                    s = new UndergraduateStudent(name, email, id, dept);
                }

                s.setGPA(gpa);
                manager.addLoadedStudent(s);
            }

        } catch (IOException ignored) {}


        try (BufferedReader br = new BufferedReader(new FileReader(COURSE_FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                Course c = new Course(
                        p[0],
                        p[1],
                        Integer.parseInt(p[2]),
                        Integer.parseInt(p[3])
                );

                manager.addLoadedCourse(c);
            }

        } catch (IOException ignored) {}
    }
}