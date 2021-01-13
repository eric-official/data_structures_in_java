// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL5_Group14;

public class PVL5_Group14 {
    public static void main(String[] args){
        Set s1 = new Set(1);
        Set s2=new Set(2);
        Set s3=new Set(2);
        Set s4=new Set(4);
        s1.union(s2);
        s3.union(s4);
        s1.printSet();
        Set lel = s1.cut(s3);
        lel.printSet();
        System.out.println(s1.isSubsetOf(s3));
    }
}
