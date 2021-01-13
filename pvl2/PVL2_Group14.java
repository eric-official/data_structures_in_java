//Abgabe Eric Näser (Matrikel: 524319)
package PVL2_Group14;

public class PVL2_Group14 implements StudentAdmin.StudentAdministration {
    private ProcessImplementation front;

    public PVL2_Group14(){
        this.front=null;
    }

    public ProcessImplementation getFront() {
        return front;
    }

    public void setFront(ProcessImplementation front) {
        this.front = front;
    }

    public ProcessImplementation sortedInsert(ProcessImplementation head, ProcessImplementation newNode) {
        ProcessImplementation current;

        if (head == null)
            head = newNode;

        else if (head.getPriority() >= newNode.getPriority()) {
            newNode.setNext(head);
            newNode.getNext().setPrev(newNode);
            head = newNode;
        } else {
            current = head;

            while (current.getNext() != null && current.getNext().getPriority() < newNode.getPriority()){
                current = current.getNext();
            }

            newNode.setNext(current.getNext());

            if (current.getNext() != null)
                newNode.getNext().setPrev(newNode);

            current.setNext(newNode);
            newNode.setPrev(current);

        }
        return head;
    }

    public void delete(ProcessImplementation del){
        ProcessImplementation le = this.getFront();
        while (le.getNext() != null && !le.getPriority().equals(del.getPriority())){
            if(le.getNext().getPriority().equals(del.getPriority())){
                if(le.getNext().getNext()!=null){
                    le.setNext(le.getNext().getNext());
                    le.getNext().setPrev(le);
                }else{
                    le.setNext(null);
                    break;
                }
            }
            le = le.getNext();
        }
    }

    @Override
    public Integer matriculate(String firstName, String lastName) {
        if(firstName==null || lastName==null) return null;

        Integer setMatrikel=1;
        ProcessImplementation current=this.front;
        while(current!=null && current.getPriority().equals(setMatrikel) && setMatrikel<=1000){
            setMatrikel++;
            current=current.getNext();
        }
        if(setMatrikel==1000) return -1;

        this.setFront(sortedInsert(this.front, new ProcessImplementation(firstName, lastName, setMatrikel)));

        return setMatrikel;
    }

    @Override
    public Boolean deregister(Integer matriculationNumber) {
        if(matriculationNumber==null) return null;

        ProcessImplementation it=this.getFront();
        Boolean res=Boolean.FALSE;
        while (it != null) {
            if(it.getPriority().equals(matriculationNumber)){
                delete(it);
                res=Boolean.TRUE;
                return res;
            }
            it = it.getNext();
        }
        return res;
    }

    @Override
    public String find(Integer matriculationNumber) {
        if(matriculationNumber==null) return null;

        String res="";
        Boolean checker=Boolean.FALSE;
        ProcessImplementation it=this.getFront();

        while (it != null) {

            if(it.getPriority()==matriculationNumber){
                checker=Boolean.TRUE;
                break;
            }
            it = it.getNext();
        }

        if(checker==Boolean.TRUE){
            res+=String.format("$%s\n$%s\n$%d\n", it.getFirstName(), it.getLastName(), it.getPriority());
            Lehrveranstaltung lv=it.getFront();
            while(lv != null){
                res+=String.format("$%s\t$%d\t$%b\n", lv.getName(), lv.getNumberOfTries(), lv.getBestanden());
                lv=lv.getNext();
            }
            return res;
        }
        return null;
    }

    @Override
    public String takeExam(Integer matriculationNumber, String courseID, Boolean passed) {
        if(matriculationNumber==null || courseID==null || passed==null) return null;

        Boolean checker=Boolean.FALSE;
        ProcessImplementation it=this.getFront();

        while (it != null) { //nach student mit matrikel suchen

            if(it.getPriority()==matriculationNumber){
                checker=Boolean.TRUE;
                break;
            }
            it = it.getNext();
        }

        if(checker==Boolean.FALSE){ //falls matrikel nicht existiert
            return null;
        }

        checker=Boolean.FALSE;
        Lehrveranstaltung lv=it.getFront();

        while(lv != null){ //nach kurs mit kursid suchen
            if(lv.getName().equals(courseID)){
                checker=Boolean.TRUE;
                break;
            }
            lv=lv.getNext();

        }

        String res_string="";
        if(checker==Boolean.TRUE){ //wenn kurs schon existiert
            lv.setNumberOfTries(lv.getNumberOfTries()+1);
            lv.setBestanden(passed);

            checker=lv.getBestanden();
            Integer res_tries=lv.getNumberOfTries();
            if(lv.getNumberOfTries()==3 && lv.getBestanden()==Boolean.FALSE) deregister(matriculationNumber);

            res_string+=String.format("$%s\t$%d\t$%b", courseID, res_tries, checker);
            return res_string;
        } else {
            it.LVaddFront(courseID, passed);
            res_string+=String.format("$%s\t$1\t$%b", courseID, passed);
            return res_string;
        }
    }

    @Override
    public String dataBase() {
        String res="";
        ProcessImplementation it=this.getFront();
        while (it != null)
        {
            res+=String.format("%s\n", find(it.getPriority()));
            it = it.getNext();
        }

        return res;
    }

    public static void main(String[] args){
        PVL2_Group14 a1=new PVL2_Group14();
        a1.matriculate("Eric", "Näser");
        a1.matriculate("Gandalf", "der Graue");
        a1.matriculate("Bilbo", "Beutlin");
        a1.deregister(2);
        a1.matriculate("Gandalf", "der Weise");
        System.out.println(a1.dataBase());
        System.out.println("-----------");
        System.out.println(a1.find(2));
        System.out.println(a1.takeExam(1, "AUP", Boolean.TRUE));
        System.out.println(a1.takeExam(1, "Mathe", Boolean.FALSE));
        System.out.println(a1.takeExam(1, "Datenstrukturen", Boolean.TRUE));
        System.out.println(a1.takeExam(null, "Mathe", Boolean.FALSE));
        System.out.println(a1.takeExam(2, "Mathe", Boolean.FALSE));
        System.out.println(a1.takeExam(2, "Mathe", Boolean.FALSE));
        System.out.println(a1.takeExam(2, "Mathe", Boolean.TRUE));
        System.out.println(a1.takeExam(5, "Mathe", Boolean.TRUE));
        System.out.println(a1.takeExam(2, "Englisch", Boolean.TRUE));
        System.out.println(a1.find(2));
        System.out.println("--------------");
        System.out.println(a1.dataBase());
    }
}
