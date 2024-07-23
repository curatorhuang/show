package student;

public class Main {
    public static void main(String[] args) {
        Class myClass = new Class();
        myClass.addStudent(new Student("张三", "Female", 20));
        myClass.addStudent(new Student("李四", "Male", 22));

        myClass.printStudents();
    }
}