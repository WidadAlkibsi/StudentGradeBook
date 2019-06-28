/*
Widad Khalid Alkibsi
1707382
DAR 
widad.alk@gmail.com
Project 3
 */
package SpellCheck;

/**
 *
 * @author widad
 */
public class englishWord {
    
private String word; 
private englishWord left;       
private englishWord right;

    public String getWord() {
        return word;
    }

    public englishWord(String word) {
        this.word = word;
    }
    

    public englishWord() {
    }

    public englishWord(String word, englishWord left, englishWord right) {
        this.word = word;
        this.left = left;
        this.right = right;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public englishWord getLeft() {
        return left;
    }

    public void setLeft(englishWord left) {
        this.left = left;
    }

    public englishWord getRight() {
        return right;
    }

    public void setRight(englishWord right) {
        this.right = right;
    }

}
