package fcitgradebook;
//shahad yousef kenani - GBR - skenani0002@stu.kau.edu.sa

import java.io.PrintWriter;

public class FCITclassRouster {

    private Student head;
    private String courseNumber;

    public boolean isEmpty() {
        //If head is null 
        return head == null;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public boolean searchID(int ID) {
        //Sends reference to the first node and the data to be searched
        return searchID(head, ID);

    }

    private boolean searchID(Student Head, int ID) {

        Student helpPtr = Head;
        while (helpPtr != null) {
            //If data is found
            if (helpPtr.getID() == ID) {
                return true;
            }
            helpPtr = helpPtr.getNext();
        }
        //If data is not found 
        return false;
    }

    public boolean searchName(String ID) {
        return searchName(head, ID);

    }

    private boolean searchName(Student Head, String ID) {
        Student helpPtr = Head;
        while (helpPtr != null) {
            String searchForName = helpPtr.getFirstName() + " " + helpPtr.getLastName();
            //If data is found
            if (searchForName.equals(ID)) {
                return true;
            }
            helpPtr = helpPtr.getNext();
        }
        //If data is not found 
        return false;
    }

    public Student findNodeForID(int ID) {
        return findNodeForID(head, ID);
    }

    private Student findNodeForID(Student p, int ID) {
        Student helpPtr = p;
        while (helpPtr != null) {
            //If data is found
            if (helpPtr.getID() == ID) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        //If data is not found 
        return null;
    }

    public Student findNode() {
        return head;
    }

    public Student findNodeForName(String ID) {
        return findNodeForName(head, ID);
    }

    private Student findNodeForName(Student p, String ID) {

        Student helpPtr = p;
        while (helpPtr != null) {
            String searchForNameNode = helpPtr.getFirstName() + " " + helpPtr.getLastName();

            if (searchForNameNode.equals(ID)) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public void insert(Student ID) {
        head = insert(head, ID);

    }

    private Student insert(Student head, Student ID) {

        if (head == null || head.getID() > ID.getID()) {
            head = new Student(ID.getID(), ID.getFirstName(), ID.getLastName(), ID.getFinalGrade(), ID.getLetterGrade(), ID.getCourseNumber(), head);
            for (int i = 0; i < 3; i++) {
                head.setExamGrades(ID.getExamGrades(i), i);
            }
            return head;
            // Insert the new node at the correct location
        } else {
            Student helpPtr = head;
            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getID() > ID.getID()) {
                    break;
                }
                //for traverse 
                helpPtr = helpPtr.getNext();
            }
            Student newNode = new Student(ID.getID(), ID.getFirstName(), ID.getLastName(), ID.getFinalGrade(), ID.getLetterGrade(), ID.getCourseNumber(), helpPtr.getNext());
            for (int i = 0; i < 3; i++) {
                newNode.setExamGrades(ID.getExamGrades(i), i);
            }
            helpPtr.setNext(newNode);
        }
        return head;

    }

    public void delete(int ID, PrintWriter output) {
        head = delete(head, ID, output);

    }

    private Student delete(Student head, int ID, PrintWriter output) {

        if (head.getID() == ID) {
            output.println("	" + head.getFirstName() + " " + head.getLastName() + " (ID# " + head.getID() + ") has been deleted from " + head.getCourseNumber() + ".");
            head = head.getNext();
        } else {
            Student helpPtr = head;
            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getID() == ID) {
                    output.println("		" + head.getFirstName() + " " + head.getLastName() + "(ID#" + head.getID() + ") has been deleted from " + head.getCourseNumber() + ".");
                    helpPtr.setNext(helpPtr.getNext().getNext());
                    break;
                }
                helpPtr = helpPtr.getNext();
            }

            return head;
        }
        return head;
    }

    public void printRoster(PrintWriter output) {
        printRoster(head, output);
    }

    private void printRoster(Student head, PrintWriter output) {
        String ExamCommand[] = {"Exam 1:    ", "Exam 2:    ", "Final Exam:"};
        Student helpPtr = head;
        while (helpPtr != null) {
            output.println("	" + helpPtr.getFirstName() + " " + helpPtr.getLastName() + " (ID# " + helpPtr.getID() + "):");
            for (int i = 0; i < 3; i++) {
                output.println("		" + ExamCommand[i] + "   " + helpPtr.getExamGrades(i));
            }
            output.printf("		Final Grade:  " + "%.2f", helpPtr.getFinalGrade());
            output.println();
            output.println("		Letter Grade: " + helpPtr.getLetterGrade());
            helpPtr = helpPtr.getNext();
        }

    }

    public void printStats(PrintWriter output) {
        printStats(head, output);

    }

    private void printStats(Student head, PrintWriter output) {

        int TotalA = 0;
        double PerA = 0;
        //--
        int TotalB = 0;
        double PerB = 0;
        //--
        int TotalD = 0;
        double PerD = 0;
        //--
        int TotalC = 0;
        double PerC = 0;
        //--
        int TotalF = 0;
        double PerF = 0;
        //--
        int count = 0;
        float AverageScore = 0;
        float HighestScore = 0;
        float LowestScore = 1000;
        Student helpPtr = head;
        while (helpPtr != null) {

            AverageScore += helpPtr.getFinalGrade();

            if (helpPtr.getFinalGrade() > HighestScore) {
                HighestScore = helpPtr.getFinalGrade();
            }

            if (helpPtr.getFinalGrade() < LowestScore) {
                LowestScore = helpPtr.getFinalGrade();
            }

            if (helpPtr.getLetterGrade() == 'A') {
                TotalA++;
            }

            if (helpPtr.getLetterGrade() == 'B') {
                TotalB++;
            }
            if (helpPtr.getLetterGrade() == 'C') {
                TotalC++;
            }
            if (helpPtr.getLetterGrade() == 'D') {
                TotalD++;
            }
            if (helpPtr.getLetterGrade() == 'F') {
                TotalF++;
            }

            helpPtr = helpPtr.getNext();
            count++;
        }
        int totalGrade = (TotalA) + (TotalB) + (TotalC) + (TotalD) + (TotalF);
        AverageScore = AverageScore / count;
        PerA = (TotalA * 100) / totalGrade;
        PerB = (TotalB * 100) / totalGrade;
        PerC = (TotalC * 100) / totalGrade;
        PerD = (TotalD * 100) / totalGrade;
        PerF = (TotalF * 100) / totalGrade;

        output.printf("	Average Score: " + "%.2f", AverageScore);
        output.println();
        output.printf("	Highest Score: " + "%.2f", HighestScore);
        output.println();
        output.printf("	Lowest Score:  " + "%.2f", LowestScore);
        output.println();
        output.printf("	Total 'A' Grades: " + TotalA);
        output.printf(" (" + "%.2f", PerA);
        output.println("% of class)");
        output.printf("	Total 'B' Grades: " + TotalB);
        output.printf(" (" + "%.2f", PerB);
        output.println("% of class)");
        output.printf("	Total 'C' Grades: " + TotalC);
        output.printf(" (" + "%.2f", PerC);
        output.println("% of class)");
        output.printf("	Total 'D' Grades: " + TotalD);
        output.printf(" (" + "%.2f", PerD);
        output.println("% of class)");
        output.printf("	Total 'F' Grades: " + TotalF);
        output.printf(" (" + "%.2f", PerF);
        output.println("% of class)");
        output.println();

    }

}
