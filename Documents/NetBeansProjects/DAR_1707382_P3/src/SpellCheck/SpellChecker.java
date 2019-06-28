/*
 Widad Khalid Alkibsi
 1707382
 DAR 
 widad.alk@gmail.com
 Project 3
 */
package SpellCheck;

import java.util.*;
import java.io.*;

public class SpellChecker {

    public static void main(String[] args) throws java.io.FileNotFoundException {

        File f = new File("words_list.txt");
        File f2 = new File("SpellChecker.in.txt");

//Check if files exist
        if (!f.exists() && !f2.exists()) {
            System.out.println("One of the files does not exist");
            System.exit(0);
        }

        Scanner wordslist = new Scanner(f);
        Scanner input = new Scanner(f2);

        File out = new File("SpellChecker.txt");
        try (
                PrintWriter output = new PrintWriter(out);) {
            englishWords words = new englishWords();
//add all the list words to the binary tree
            String word;
            while (wordslist.hasNext()) {
                word = wordslist.nextLine();
                words.insert(word);

            }
            /*
             Used to check my words list
             words.inorder();
             */

        //After Adding all the words list we start the searching the words in the binary list 
            String data;
            while (input.hasNext()) {
                data = input.next();

                if (words.search(data)) {
                    output.print(" " + data);
                } else {
                    output.print(" \"" + data + "\"");
                }

            }

        }
    }

}
