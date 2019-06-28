package fcitgradebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Fcitgradebook {

    public static void main(String[] args) throws FileNotFoundException {

        //reader File 
        File inputFile = new File("FCITgradeBook.in.txt");
        //cheaking
        if (!inputFile.exists()) {
            System.out.println("Input file, " + inputFile + ", does not exist."); // show massage if inputfile does not exists
            System.exit(0);
        }
        //writer File 
        File outputFile = new File("FCITgradeBook.out.txt");

        try (
                //Scanner to read from input
                Scanner MS = new Scanner(inputFile);
                //Printwriter to print on output
                PrintWriter output = new PrintWriter(outputFile)) {
            output.println("Welcome to the FCIT Grade Book.");
            output.println();
            int numOfCourses = MS.nextInt();
            FCITclassRouster[] courses = new FCITclassRouster[numOfCourses];
            int CountStuNum = 0;
            //--------------------------------------------------------------------------
            for (int i = 0; i < numOfCourses; i++) {
                courses[i] = new FCITclassRouster();
                courses[i].setCourseNumber(MS.next());
            }
            output.println("The following course(s) have been added to the database:");
            output.println("	" + courses[0].getCourseNumber() + "\r\n	" + courses[1].getCourseNumber() + "\r\n	" + courses[2].getCourseNumber());
            output.println();
            String ExamCommand[] = {"Exam 1:    ", "Exam 2:    ", "Final Exam:"};
            //---------------------------------------------------------------------------
            String courseOfAll;
            String command;
            while (MS.hasNext()) {
                do {
                    command = MS.next();
                    switch (command) {
                        //1--------------------------------------------------------------------
                        case "DISPLAYSTATS":
                            // check1 find or not
                            int check1 = 0;
                            courseOfAll = MS.next();
                            if (courseOfAll.equals("ALL")) {
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
                                double AverageScore = 0;
                                double HighestScore = 0;
                                double LowestScore = 1000;
                                //--
                                for (int i = 0; i < 3; i++) {
                                    if (!courses[i].isEmpty()) {
                                        Student Cor1 = courses[i].findNode();
                                        Student helpPtr = Cor1;

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
                                            check1 = 1;
                                        }

                                    }
                                }

                                double totalGrade = (TotalA) + (TotalB) + (TotalC) + (TotalD) + (TotalF);
                                AverageScore = AverageScore / CountStuNum;

                                if (totalGrade == 0) {
                                } else {
                                    PerA = (TotalA * 100) / totalGrade;
                                    PerB = (TotalB * 100) / totalGrade;
                                    PerC = (TotalC * 100) / totalGrade;
                                    PerD = (TotalD * 100) / totalGrade;
                                    PerF = (TotalF * 100) / totalGrade;
                                }
                                if (check1 == 0) {
                                    LowestScore = 0;
                                    AverageScore = 0;
                                }

                                output.println("Command: DISPLAYSTATS (ALL)");
                                output.println("Statistical Results for All Courses:");
                                output.println("	Total number of student records: " + CountStuNum);
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

                            } else {
                                int StuNum = 0;
                                for (int i = 0; i < 3; i++) {
                                    if (courses[i].getCourseNumber().equals(courseOfAll)) {
                                        Student SN = courses[i].findNode();

                                        while (SN != null) {
                                            StuNum++;
                                            SN = SN.getNext();
                                        }
                                    }

                                }
                                output.println("Command: DISPLAYSTATS (" + courseOfAll + ")");
                                output.println("Statistical Results of " + courseOfAll + ":");
                                output.println("	Total number of student records: " + StuNum);

                                for (int i = 0; i < 3; i++) {
                                    if (courses[i].getCourseNumber().equals(courseOfAll)) {
                                        courses[i].printStats(output);
                                    }
                                }
                            }

                            break;

                        //2------------------------
                        case "DELETERECORD":
                            output.println("Command: DELETERECORD");
                            // check2 find or not
                            int check2 = 0;
                            int DeleteID = MS.nextInt();
                            for (int i = 0; i < courses.length; i++) {
                                if (courses[i].searchID(DeleteID)) {
                                    courses[i].delete(DeleteID, output);
                                    check2 = 1;
                                    CountStuNum--;
                                }
                            }
                            output.println();
                            // if the id is not find
                            if (check2 == 0) {
                                output.println(">    " + DeleteID + " is not in the list (no delete needed).");
                            }
                            break;
                        //3--------------------------------------
                        case "DISPLAYSTUDENTS":
                            // check3 find or not
                            int check3 = 0;
                            courseOfAll = MS.next();
                            if (courseOfAll.equals("ALL")) {
                                for (int i = 0; i < 3; i++) {
                                    if (courses[i].isEmpty()) {
                                    } else {
                                        check3 = 1;
                                    }
                                }

                                if (check3 == 1) {
                                    output.println("Command: DISPLAYSTUDENTS (ALL)");
                                    for (int i = 0; i < 3; i++) {
                                        if (!courses[i].isEmpty()) {
                                            output.println("Course Roster for " + courses[i].getCourseNumber() + ":");
                                            courses[i].printRoster(output);
                                            check3 = 1;
                                        }
                                    }

                                    output.println();
                                }

                                if (check3 == 0) {
                                    output.println("Command: DISPLAYSTUDENTS (ALL)\r\n	ERROR: there are no students currently in the system.");
                                    output.println();
                                }

                            } else {

                                for (int i = 0; i < 3; i++) {
                                    if (courses[i].getCourseNumber().equals(courseOfAll)) {
                                        if (courses[i].isEmpty()) {

                                        } else {
                                            check3 = 1;
                                        }
                                    }
                                }

                                if (check3 == 1) {
                                    output.println("Command: DISPLAYSTUDENTS (" + courseOfAll + ")");

                                    for (int i = 0; i < 3; i++) {
                                        if (courses[i].getCourseNumber().equals(courseOfAll)) {
                                            output.println("Course Roster for " + courseOfAll + ":");
                                            courses[i].printRoster(output);
                                            output.println();
                                        }
                                    }
                                }

                                if (check3 == 0) {
                                    output.println("Command: DISPLAYSTUDENTS (" + courseOfAll + ")");
                                    output.println("	ERROR: there are no student records for " + courseOfAll + ".");
                                    output.println();
                                }
                            }
                            break;
                        //4----------------------------------------
                        case "SEARCHBYID":
                            output.println("Command: SEARCHBYID");
                            // check4 if the id find or not
                            int check4 = 0;
                            int ID = MS.nextInt();
                            int found = 0;
                            for (FCITclassRouster course : courses) {
                                if (course.searchID(ID)) {
                                    Student IDForStu = course.findNodeForID(ID);
                                    if (found == 0) {
                                        output.println("Student Record for " + IDForStu.getFirstName() + " " + IDForStu.getLastName() + " (ID# " + IDForStu.getID() + "):");
                                    }
                                    output.println("	Course: " + IDForStu.getCourseNumber());
                                    found++;
                                    for (int i = 0; i < 3; i++) {
                                        output.println("		" + ExamCommand[i] + "   " + IDForStu.getExamGrades(i));
                                    }
                                    output.printf("		Final Grade:  " + "%.2f", +IDForStu.getFinalGrade());
                                    output.println();
                                    output.println("		Letter Grade: " + IDForStu.getLetterGrade());
                                    check4 = 1;
                                }
                            }
                            // if not find 
                            if (check4 == 0) {
                                output.println("	ERROR: there is no record for student ID# " + ID + ".");
                            }
                            output.println();
                            break;
                        //5-----------------------------------
                        case "SEARCHBYNAME":
                            output.println("Command: SEARCHBYNAME");
                            // check5 if the id find or not
                            int check5 = 0;
                            String SearchForName = MS.next() + " " + MS.next();

                            for (FCITclassRouster course : courses) {
                                if (course.searchName(SearchForName)) {
                                    Student Find = course.findNodeForName(SearchForName);
                                    output.println("Student Record for " + Find.getFirstName() + " " + Find.getLastName() + " (ID# " + Find.getID() + "):");
                                    output.println("	Course: " + Find.getCourseNumber());
                                    check5 = 1;
                                    for (int i = 0; i < 3; i++) {
                                        output.println("		" + ExamCommand[i] + "   " + Find.getExamGrades(i));
                                    }
                                    output.printf("		Final Grade:  " + "%.2f", +Find.getFinalGrade());
                                    output.println();
                                    output.println("		Letter Grade: " + Find.getLetterGrade());
                                    output.println();
                                }
                            }
                            // if not find 
                            if (check5 == 0) {
                                output.println("	ERROR: there is no record for student \"" + SearchForName + "\".");
                                output.println();
                            }

                            break;
                        //6--------------------------------------
                        case "ADDRECORD":

                            output.println("Command: ADDRECORD");
                            String courseNum = MS.next();
                            int StuID = MS.nextInt();
                            String FName = MS.next();
                            String LName = MS.next();
                            int[] examsGrade = new int[3];
                            // this check6 for the course if there is or not
                            int check6 = 0;
                            char LetterGrade = ' ';
                            // create new opject to add to the linklist
                            Student fullInfo = new Student(StuID, FName, LName, courseNum);
                            for (int i = 0; i < examsGrade.length; i++) {
                                int e = MS.nextInt();
                                // set the Greads in the node
                                fullInfo.setExamGrades(e, i);
                            }
                            float FinalGrade = (float) (fullInfo.getExamGrades(0) * (0.3) + fullInfo.getExamGrades(1) * (0.3) + fullInfo.getExamGrades(2) * (0.4));

                            if (FinalGrade >= 90) {
                                LetterGrade = 'A';
                            } else if (FinalGrade >= 80) {
                                LetterGrade = 'B';
                            } else if (FinalGrade >= 70) {
                                LetterGrade = 'C';
                            } else if (FinalGrade >= 60) {
                                LetterGrade = 'D';
                            } else if (FinalGrade < 60) {
                                LetterGrade = 'F';
                            }
                            // set the final Grade to the node to the linklist
                            fullInfo.setFinleGrade(FinalGrade);
                            // set the Letter Grade to the node to the linklist
                            fullInfo.setLetterGrade(LetterGrade);
                            // search if the course find or not 
                            for (FCITclassRouster course : courses) {
                                // if find the corse
                                if (course.getCourseNumber().equals(courseNum)) {
                                    course.insert(fullInfo);
                                    output.println("	" + FName + " " + LName + " (ID# " + StuID + ") has been added to " + courseNum + ".");
                                    output.printf("	His final grade is " + "%.2f", FinalGrade);
                                    output.println(" (" + LetterGrade + ").");
                                    output.println();
                                    check6 = 1;
                                    CountStuNum++;
                                }
                            }
                            // if not find the corse
                            if (check6 == 0) {
                                output.println("	ERROR: cannot add student. Course \"" + courseNum + "\" does not exist.");
                                output.println();
                            }
                            break;
                    }

                } while (command.equals("QUIT") == false);

            }

            output.println("Thank you for using the the FCIT Grade Book.");
            output.println();
            output.println("Goodbye.");
            output.close(); // close the output

        
    
}}}
