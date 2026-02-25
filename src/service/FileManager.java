package service;

import model.*;
import java.io.*;

public class FileManager {

    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";

    public void saveData(UniversityManager manager) {
        try(PrintWriter sw = new PrintWriter(new FileWriter(STUDENT_FILE));
            PrintWriter cw = new PrintWriter(new FileWriter(COURSE_FILE))) {

            for(Student s : manager.getAllStudents().values()) {
                sw.println(s.getStudentID() + "," + s.getName() + "," + s.getEmail() + "," + s.getDepartment() + "," + s.getGPA());
            }

            for(Course c : manager.getAllCourses().values()) {
                cw.println(c.getCode() + "," + c.getTitle() + "," + c.getCapacity());
            }

        } catch(IOException e) { System.out.println("Error saving data."); }
    }

    public void loadData(UniversityManager manager) {

        try(BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while((line=br.readLine())!=null) {
                if(line.isEmpty()) continue;
                String[] p = line.split(",");
                if(p.length<4) continue;
                Student s = new Student(p[1],p[2],p[0],p[3]);
                if(p.length>=5) {
                    try{ s.setGPA(Double.parseDouble(p[4])); } catch(Exception e){ s.setGPA(0); }
                }
                manager.addLoadedStudent(s);
            }
        } catch(IOException ignored) {}


        try(BufferedReader br = new BufferedReader(new FileReader(COURSE_FILE))) {
            String line;
            while((line=br.readLine())!=null) {
                if(line.isEmpty()) continue;
                String[] p = line.split(",");
                if(p.length<3) continue;
                int cap = 10;
                try{
                    cap=Integer.parseInt(p[2]);
                } catch(Exception ignored){}
                Course c = new Course(p[0],p[1],cap);
                manager.addLoadedCourse(c);
            }
        } catch(IOException ignored) {}
    }
}