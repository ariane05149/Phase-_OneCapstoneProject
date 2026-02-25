package app;

import model.*;
import service.*;
import exception.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();
        FileManager fileManager = new FileManager();
        fileManager.loadData(manager);

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--------Welcome to the UniManagement System---------");
                System.out.println("1. Register Student");
                System.out.println("2. Create Course");
                System.out.println("3. Enroll Student");
                System.out.println("4. View Student Record");
                System.out.println("5. Department Average GPA");
                System.out.println("6. Top Student");
                System.out.println("7. Dean's List (GPA>3.5)");
                System.out.println("8. Save & Exit");
                System.out.print("Choose option: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input.");
                    sc.next();
                    continue;
                }
                int choice = sc.nextInt();
                sc.nextLine();

                try {
                    switch (choice) {

                        case 1:
                            System.out.print("ID: ");
                            String id = sc.nextLine();
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("Email: ");
                            String email = sc.nextLine();
                            System.out.print("Department: ");
                            String dept = sc.nextLine();
                            System.out.print("Student Type (1=Undergrad,2=Grad): ");
                            int type = sc.nextInt();
                            sc.nextLine();
                            Student student = (type == 2) ? new GraduateStudent(name, email, id, dept)
                                    : new UndergraduateStudent(name, email, id, dept);
                            manager.registerStudent(student);
                            System.out.println("Student registered.");
                            break;

                        case 2:
                            System.out.print("Course Code: ");
                            String code = sc.nextLine();
                            System.out.print("Title: ");
                            String title = sc.nextLine();
                            System.out.print("Capacity: ");
                            int cap = sc.nextInt();
                            sc.nextLine();
                            Course c = new Course(code, title, cap);
                            manager.createCourse(c);
                            System.out.println("Course created.");
                            break;

                        case 3:
                            System.out.print("Student ID: ");
                            String sid = sc.nextLine();
                            System.out.print("Course Code: ");
                            String ccode = sc.nextLine();
                            System.out.print("Grade for course: ");
                            double grade = sc.nextDouble();
                            sc.nextLine();
                            manager.enroll(sid, ccode, grade);
                            System.out.println("Enrollment successful.");
                            break;

                        case 4:
                            System.out.print("Student ID: ");
                            String vid = sc.nextLine();
                            Student s = manager.getStudent(vid);
                            if (s == null) System.out.println("Student not found.");
                            else {
                                System.out.println("--- Student Record ---");
                                System.out.println("Name: " + s.getName());
                                System.out.println("Department: " + s.getDepartment());
                                System.out.println("GPA: " + s.getGPA());
                                System.out.println("Courses & Grades:");
                                if (s.getEnrolledCourses().isEmpty()) System.out.println("No courses enrolled.");
                                else
                                    s.getEnrolledCourses().forEach((course, g) -> System.out.println("- " + course.getCode() + " : " + course.getTitle() + " Grade: " + g));
                                System.out.println("Tuition: " + s.calculateTuition());
                            }
                            break;

                        case 5:
                            System.out.print("Department: ");
                            String d = sc.nextLine();
                            System.out.println("Average GPA: " + manager.calculateAverageGPA(d));
                            break;

                        case 6:
                            Student top = manager.getTopStudent();
                            if (top != null)
                                System.out.println("Top Student: " + top.getName() + " GPA: " + top.getGPA());
                            else System.out.println("No students.");
                            break;

                        case 7:
                            System.out.println("--- Dean's List ---");
                            manager.getAllStudents().values().stream()
                                    .filter(st -> st.getGPA() > 3.5)
                                    .forEach(st -> System.out.println(st.getName() + " GPA: " + st.getGPA()));
                            break;

                        case 8:
                            fileManager.saveData(manager);
                            System.out.println("Data saved. Exiting.");
                            return;

                        default:
                            System.out.println("Invalid option. Choose 1-8.");
                    }
                } catch (CourseFullException | StudentAlreadyEnrolledException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}