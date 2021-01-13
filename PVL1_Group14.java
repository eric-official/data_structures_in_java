//Abgabe Eric Näser (Matrikel: 524319)
package PVL1_Group14;
import java.util.ArrayList;
import java.util.ListIterator;

public class PVL1_Group14 {
    private int n;
    private int k;
    private ArrayList<Zustand>zustand_list;
    private ArrayList<Uebergang>uebergang_list;

    public PVL1_Group14(int data[][]){
        this.n=data[0][0];
        this.k=data[1][0];
        this.zustand_list=new ArrayList<Zustand>();
        this.uebergang_list=new ArrayList<Uebergang>();

        for(int i=0;i<n;i++){
            this.zustand_list.add(new Zustand(i));
        }

        for(int i=0;i<n;i++){
            for(int y=0;y<data[3+i].length;y+=2){
                this.uebergang_list.add(new Uebergang(this.zustand_list.get(i), data[3+i][y], this.zustand_list.get(data[3+i][y+1])));
            }
        }
    }

    public void setTransition(int state, int transition, int to){
        int checker=0;
        int x=0;
        for(int i=0; i<this.uebergang_list.size(); i++){
            if(uebergang_list.get(i).get_prev()==state) checker++;          //zählen wieviele transitions von state exisitieren
        }

        if(checker==this.k && uebergang_list.size()<=this.n*this.k){    //wenn überschrieben werden muss
            while(uebergang_list.get(x).get_prev()!=state || uebergang_list.get(x).get_data()!=transition) {
                x++;
            }
            uebergang_list.set(x, new Uebergang(zustand_list.get(state), transition, zustand_list.get(to)));
        }
        if(checker<this.k && uebergang_list.size()<=this.n*this.k){     // wenn hinzugefügt werden kann
            this.uebergang_list.add(new Uebergang(zustand_list.get(state), transition, zustand_list.get(to)));
        }
    }

    public void deleteTransition(int state, int transition){
        int i=0;
        int pos=-1;
        while(i<uebergang_list.size()){
            if(uebergang_list.get(i).get_prev()==state && uebergang_list.get(i).get_data()==transition ) pos=i;
            i++;
        }
        if(pos!=-1) uebergang_list.remove(pos);
    }

    public int find_state_trans(int state, int transition){
        for(int i=0; i<uebergang_list.size(); i++){
            if(uebergang_list.get(i).get_prev()==state && uebergang_list.get(i).get_data()==transition) return i;
        }
        return -1;
    }

    public int traverse_input(int startState, int[] transitions){
        int i=0;
        int checker;
        for(i=0;i<transitions.length; i++){
            checker=find_state_trans(startState, transitions[i]);
            System.out.println(startState + " " + transitions[i] + " " + checker);
            if(checker!=-1) startState=uebergang_list.get(checker).get_next();
            else return -1;
        }

        if(i==transitions.length) return startState;
        else return -1;
    }

    public void print_zustand(){ //zustände ausgeben
        ListIterator<Zustand> li = this.zustand_list.listIterator();
        while(li.hasNext()){
            System.out.println(li.next().get_data());
            System.out.println(" ");
        }
    }

    public void print_ueb(){ //uebergänge ausgeben
        for(int i=0; i<this.uebergang_list.size(); i++){
            System.out.println(uebergang_list.get(i).get_prev());
            System.out.println(uebergang_list.get(i).get_data());
            System.out.println(uebergang_list.get(i).get_next());
            System.out.println(" ");
        }
    }
}
