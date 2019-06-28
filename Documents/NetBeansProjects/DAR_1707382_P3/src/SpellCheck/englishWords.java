/*
Widad Khalid Alkibsi
1707382
DAR 
widad.alk@gmail.com
Project 3
 */
package SpellCheck;

public class englishWords {

    private englishWord root;

    // CONSTRUCTORS
    public englishWords() {
        root = null;
    }

    
    public boolean isEmpty() {
        return root == null;
    }

    public boolean search(String word) {
        return search(root, word);
    }
   
    private boolean search(englishWord root, String word) {

        if (root == null) {
            return false;
        } else {
            // if the word itself equal to the root
            if (word.equals(root.getWord())) {
                return true;
                //check if the word is less or more than the root
            } else if (word.compareTo(root.getWord()) > 0) {
                //check in the left of of then tree
                return search(root.getLeft(), word);
            } else {
                //check on the right
                return search(root.getRight(), word);
            }

        }

    }

    public void insert(String word) {
        englishWord newWord = new englishWord(word);
        root = insert(root, newWord);
    }
	//
    // BSTnode | insert(BSTnode, BSTnode)
    //

    private englishWord insert(englishWord root, englishWord newWord) {
        // IF there is no tree, newWord will be the root, so just return it
        if (root == null) {
            return newWord;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root
            if (newWord.getWord().compareTo(root.getWord()) < 0) {
				// Recursively insert into the right subtree
                // The result of insertion is an updated root of the right subtree
                englishWord temp = insert(root.getRight(), newWord);
                // Save this newly updated root of right subtree into root.right
                root.setRight(temp);
            } // Insert to the LEFT of root
            else {
				// Recursively insert into the left subtree
                // The result of insertion is an updated root of the left subtree
                englishWord temp = insert(root.getLeft(), newWord);
                // Save this newly updated root of left subtree into root.left
                root.setLeft(temp);
            }
        }
        // Return root of updated tree
        return root;
    }

    //To check my Lists 
    public void inorder() {
        inorder(root);
    }
    //
    // void | inorder(BSTnode)
    //

    private void inorder(englishWord word) {
        if (word != null) {
            inorder(word.getLeft());
            System.out.print(word.getWord() + ", ");
            inorder(word.getRight());
        }
    }

}
//Class to create the BST with insert, search and other methods
