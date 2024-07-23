package student;

import java.util.ArrayList;
import java.util.List;

public class Class {
    private List<Student> students;

    public Class() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void printStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}