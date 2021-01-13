// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL4_Group14;

public class TreeNode {
    private TreeNode right;
    private TreeNode left;
    private char element;
    private float probability;

    public TreeNode(char element, float probability) {
        this.element = element;
        this.probability = probability;
        this.right = null;
        this.left = null;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public char getElement() {
        return element;
    }

    public void setElement(char element) {
        this.element = element;
    }

    public float getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }
}
