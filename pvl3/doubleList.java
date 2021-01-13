// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package calculator_group_14;

public class doubleList {
    private doubleNode front;
    private int Klammercounter;

    public doubleList() {
        setFront(null);
    }

    public doubleList(String str) {
        this.Klammercounter = 0;

        String helper ="";
        for(int i = 0; i < str.length();i++) {

            if(str.charAt(i) != ' ') {
                helper+=str.charAt(i);
            }
            else {
                this.add(helper);
                helper = "";
            }
        }
        this.add(helper);
    }

    public boolean isEmpty() {
        return getFront() == null;
    }

    public doubleNode getFront() {
        return front;
    }

    public void setFront(doubleNode front) {
        this.front = front;
    }

    private doubleNode add (String dataValue) {


        doubleNode l = this.getFront();
        while(l != null && l.getNext() != null) {
            l=l.getNext();
        }




        char typ ='c';
        if(dataValue.charAt(0) == '+' || dataValue.charAt(0) == '*') {
            typ = 'a';
        }
        if(dataValue.charAt(0) >= '0' && dataValue.charAt(0) <= '9') {
            typ = 'b';
        }
        if(dataValue.charAt(0) == '(' || dataValue.charAt(0) == ')') {
            this.Klammercounter++;
        }


        doubleNode n = new doubleNode(dataValue, typ);

        if(this.getFront() == null) {
            this.setFront(n);
            return front;
        }

        l.setNext(n);
        n.setPrevious(l);
        n.setNext(null);
        return front;
    }

    public void printContent() {
        for (doubleNode it = getFront(); it != null; it = it.getNext()) {
            System.out.print(it.toString() + it.getZeichenTyp());
        }
    }

    public boolean isValid() {
        int checker = 0;
        doubleNode it = this.getFront();

        while(it != null) {
            char zl, zr;
            if(it.getPrevious() == null) {
                zl = 'n';
            }
            else {
                zl = it.getPrevious().getZeichenTyp();
            }
            if(it.getNext() == null) {
                zr = 'n';
            }
            else {
                zr = it.getNext().getZeichenTyp();
            }

            char s = it.getZeichenTyp();
            switch(s) {
                case 'a':
                    if(zl != 'b' && zl != 'c') {
                        return false;
                    }
                    if(zr != 'b'&& zr != 'c') {
                        return false;
                    }
                    break;

                case 'b':
                    if(zl != 'a' && zl != 'c' && zl != 'n') {
                        return false;
                    }
                    if(zr != 'a' && zr != 'c' && zr != 'n') {
                        return false;
                    }
                    break;
                case 'c':
                    if(it.getData().charAt(0)== '(') {
                        checker++;
                    }
                    if(it.getData().charAt(0)== ')') {
                        checker--;
                    }
            }
            it = it.getNext();
        }

        if(checker != 0) {

            return false;
        }
        return true;
    }

    public int calculate () {
        doubleNode it = this.getFront();
        int res;
        doubleNode helper = new doubleNode(null, '0');

        while(this.Klammercounter != 0) {
            it = this.getFront();
            while(it != null && it.getData().charAt(0)!= ')') {
                // Klammer ganz innen finden
                if(it.getData().charAt(0) =='(') {
                    helper = it;
                }
                it = it.getNext();
            }

            it = helper;
            while (it != null && !it.getData().equals(")")) { // schleife für multi
                if(it.getData().charAt(0)=='*') {
                    this.mult(it);
                }
                it = it.getNext();
            }

            it = helper;
            while (it != null && !it.getData().equals(")")) { // schleife für add
                if(it.getData().charAt(0)=='+') {
                    this.add(it);
                }
                it = it.getNext();
            }
            it = helper;
            doubleNode lel = it.getNext();

            if(lel.getPrevious().getPrevious() != null) {
                lel.getPrevious().getPrevious().setNext(lel);
                lel.setPrevious(lel.getPrevious().getPrevious());
            }
            else {
                this.setFront(lel);
                lel.setPrevious(null);
            }

            if(lel.getNext().getNext() != null) {
                lel.getNext().getNext().setPrevious(lel);
                lel.setNext(lel.getNext().getNext());
            }
            else {
                lel.setNext(null);
            }
            lel.setZeichenTyp('b');
            this.Klammercounter = this.Klammercounter -2 ;

        }

        it=this.getFront();
        while (it != null && !it.getData().equals("(")) { // schleife für multi
            if(it.getData().charAt(0)=='*') {
                this.mult(it);
            }
            it = it.getNext();
        }

        it = this.getFront();
        while (it != null && !it.getData().equals("(")) { // schleife für add
            if(it.getData().charAt(0)=='+') {
                this.add(it);
            }
            it = it.getNext();
        }
        int final_res=Integer.parseInt(this.getFront().getData());
        return final_res;
    }

    public void mult (doubleNode lel) {
        int res = 0;
        int numb1 = Integer.valueOf(lel.getPrevious().getData());
        int numb2 = Integer.valueOf(lel.getNext().getData());
        res = numb1*numb2;

        lel.setData(String.valueOf(res));

        if(lel.getPrevious().getPrevious() != null) {
            lel.getPrevious().getPrevious().setNext(lel);
            lel.setPrevious(lel.getPrevious().getPrevious());
        }
        else {
            this.setFront(lel);
            lel.setPrevious(null);
        }

        if(lel.getNext().getNext() != null) {
            lel.getNext().getNext().setPrevious(lel);
            lel.setNext(lel.getNext().getNext());
        }
        else {
            lel.setNext(null);
        }

        lel.setZeichenTyp('b');

    }

    public void add (doubleNode lel) {
        int res = 0;
        int numb1 = Integer.valueOf(lel.getPrevious().getData());
        int numb2 = Integer.valueOf(lel.getNext().getData());
        res = numb1+numb2;

        lel.setData(String.valueOf(res));

        if(lel.getPrevious().getPrevious() != null) {
            lel.getPrevious().getPrevious().setNext(lel);
            lel.setPrevious(lel.getPrevious().getPrevious());
        }
        else {
            this.setFront(lel);
            lel.setPrevious(null);
        }

        if(lel.getNext().getNext() != null) {
            lel.getNext().getNext().setPrevious(lel);
            lel.setNext(lel.getNext().getNext());
        }
        else {
            lel.setNext(null);
        }

        lel.setZeichenTyp('b');

    }

    public int getKC() { return Klammercounter; }

}
