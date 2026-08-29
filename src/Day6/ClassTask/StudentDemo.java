package Day6.ClassTask;

public class StudentDemo {
    static {
        System.out.println("|_static block executed_|");
    }

    public static void main(String[] args) {
        Student s1 = new Student(1, "AHI", "CS");
        Student s2 = new Student(2, "DEVA", "DS");
        Student s3 = new Student(3, "KAALA", "AIDS");

        s1.writeTest();
        s2.writeTest();
        s3.writeTest();
        s1.studentInfo();
        s2.studentInfo();
        s3.studentInfo();

        Student.getCollegeInfo();
        System.out.println(Student.clg);
    }

    static class Student {
        int id;
        String name;
        String dept;
        static String clg = "SNPSU";

        Student(int id, String name, String dept) {
            this.id = id;
            this.name = name;
            this.dept = dept;
        }

        public void studentInfo() {
            System.out.println("|_Student Name is_| " + name);
            System.out.println("|_Student Dept is_|" + dept);
            System.out.println("|_Student Clg is_| " + clg);
        }

        public static void getCollegeInfo() {
            System.out.println("|_College:_| " + clg);
        }

        public void writeTest() {
            System.out.println(name + " |_is writing test_|");
        }
    }
}
