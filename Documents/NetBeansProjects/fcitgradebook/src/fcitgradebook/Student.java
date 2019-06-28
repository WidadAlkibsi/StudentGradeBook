package fcitgradebook;
//shahad yousef kenani - GBR - skenani0002@stu.kau.edu.sa
public class Student {

    private String courseNumber;
    private int ID;
    private String firstName;
    private String lastName;
    private int[] examGrades;
    private float finalGrade;
    private char letterGrade;
    private static int numStudents;
    private Student next;

    public Student(int ID, Student next) {
        this.ID = ID;
        this.next = next;
    }

    public Student(int ID, String firstName, String lastName, float finleGrade, char letterGrade, String Cours, Student next) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.finalGrade = finleGrade;
        this.letterGrade = letterGrade;
        this.courseNumber = Cours;
        this.examGrades = new int[3];
        this.next = next;

    }

    public Student(int ID, String firstName, String lastName, String Cours) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseNumber = Cours;
        this.examGrades = new int[3];

    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExamGrades(int examGrades, int index) {
        this.examGrades[index] = examGrades;
    }

    public void setFinleGrade(float finleGrade) {
        this.finalGrade = finleGrade;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }

    public static void setNumStudents(int numStudents) {
        Student.numStudents = numStudents;
    }

    public void setNext(Student next) {
        this.next = next;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getExamGrades(int i) {
        return examGrades[i];
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public Student getNext() {
        return next;
    }

}