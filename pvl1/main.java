//Abgabe Eric Näser (Matrikel: 524319)
package PVL1_Group14;

public class main {
    public static void main(String[] args){
        int[][] data= {
                {4},
                {2},
                {1,2},
                {1,0, 2,1},
                {1,2, 2,3},
                {1,0},
                {}
        };

        PVL1_Group14 a1=new PVL1_Group14(data);
        a1.print_ueb();
        System.out.println("-------------");
        a1.deleteTransition(1,1);
        a1.print_ueb();
        System.out.println("-------------");
        a1.setTransition(1,1,2);
        a1.print_ueb();
        System.out.println("-------------");
        int[] arr={2,1,1,2};
        System.out.println(a1.traverse_input(0, arr));
    }
}
