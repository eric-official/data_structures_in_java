// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL4_Group14;

import com.sun.source.tree.Tree;

public class PVL4_Group14 implements HuffmanCode {
    private TreeNode root;
    private char[] a;

    public PVL4_Group14(char[] alphabet, float[] probabilities){

        this.a=new char[alphabet.length];
        for(int i=0;i<alphabet.length;i++){
            this.a[i]=alphabet[i];
        }

        root=new TreeNode(alphabet[0],probabilities[0]);

        int i=1;

        while(root.getProbability()<=1.0 && i < probabilities.length){

            TreeNode current = this.root; //wurzel die wir als kind setzen wollen

            while(current.getLeft() != null && current.getLeft().getProbability()>probabilities[i]){

                if(current.getRight().getProbability()==probabilities[i]) break;

                current.setProbability(current.getProbability() + probabilities[i]);
                current=current.getLeft();

            }

            TreeNode n1 = new TreeNode(current.getElement(), current.getProbability());
            n1.setLeft(current.getLeft());
            n1.setRight(current.getRight());
            TreeNode n2 = new TreeNode(alphabet[i], probabilities[i]);

            if(n1.getProbability() < n2.getProbability()){
                current.setLeft(n2);
                current.setRight(n1);
                current.setElement('\0');
                current.setProbability(n1.getProbability() + n2.getProbability());
            } else {
                current.setLeft(n1);
                current.setRight(n2);
                current.setElement('\0');
                current.setProbability(n1.getProbability() + n2.getProbability());
            }
            this.CheckDenShit(this.root);
            i++;
            this.printTreeInorder();
        }
    }

    public void CheckDenShit(TreeNode it){
        if(it.getLeft() != null || it.getRight() != null){
            TreeNode left = it.getLeft();
            TreeNode right = it.getRight();
            if(left.getProbability() < right.getProbability()) {
                TreeNode help = right;
                right = left;
                left = help;
            }
            CheckDenShit(left);
            CheckDenShit(right);
        }
    }

    public boolean isLeaf(TreeNode n){
        if(n.getLeft()==null && n.getRight()==null) return true;
        else return false;
    }

    public String CodeForALetter(TreeNode n, char c, String res){
        if(isLeaf(n) && n.getElement()==c) {
            return res;
        } else if(isLeaf(n) && n.getElement()!=c){
            res="";
            return res;
        }
        return CodeForALetter(n.getLeft(), c,res+"1")+CodeForALetter(n.getRight(), c,res+"0");
    }

    @Override
    public String getCodes() {
        String res="";
        for(int i=0; i<this.a.length; i++){
            res=res + this.a[i] + " - " + CodeForALetter(this.root, this.a[i], "") + "\n";
        }
        return res;
    }

    @Override
    public String encode(String plainText) {
        String res="";
        for(int i=0;i<plainText.length();i++){
            res+=CodeForALetter(this.root, plainText.charAt(i), "");
        }
        res+="\n";
        return res;
    }

    @Override
    public String decode(String huffmanText) {
        String res = "";

        int current = 0;
        TreeNode cur = root;
        while(current < huffmanText.length() ) {
            if(huffmanText.charAt(current) == '1') {
                cur = cur.getLeft();
            }
            if(huffmanText.charAt(current) == '0') {
                cur = cur.getRight();
            }

            if(cur.getElement() != '\0') {
                res = res + " " + cur.getElement();
                cur = root;
            }
            current++;
        }
        return res;
    }

    public void printTreeInorder(){
        printTreeInorder(this.root);
        System.out.println(" ");
    }

    private void printTreeInorder(TreeNode n) {
        if (n != null) {
            printTreeInorder(n.getLeft());
            System.out.print(n.getElement() + ": " + n.getProbability() + " ");
            printTreeInorder(n.getRight());
        }
    }

    public static void main(String[] args){
        char[] alphabet={'a', 'b', 'c', 'd'};
        float[] probabilities={0.4f, 0.2f, 0.1f, 0.3f};
        PVL4_Group14 erstertestbitch = new PVL4_Group14(alphabet, probabilities);
        erstertestbitch.printTreeInorder();
        System.out.println(erstertestbitch.getCodes());
        System.out.println(erstertestbitch.encode("abacdaa"));
    }
}
