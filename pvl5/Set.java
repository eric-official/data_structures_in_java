// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL5_Group14;


import java.util.Iterator;

public class Set implements InterfaceSet {

    private List<Integer> liste;

    public List<Integer> getListe() {
        return liste;
    }

    public void setListe(List<Integer> liste) {
        this.liste = liste;
    }

    public Set() {
        this.liste = new List<Integer>();
    }

    public Set(int element) {
        this.liste = new List<Integer>();
        this.liste.addFront(element);
    }

    @Override
    public Set union(Set toInsert) {
        if(toInsert == null) {
            return this;
        }
        genericNode<Integer> it = toInsert.liste.getFront();
        while (it != null) {
            if (this.liste.ListContains(it.getData()) == false) {
                this.liste.addFront(it.getData());
            }
            it = it.getNext();
        }
        return this;
    }

    @Override
    public Set cut(Set toCutWith) {
        if(toCutWith == null) {
            return null;
        }
        genericNode<Integer> it_toCut = toCutWith.getListe().getFront();
        Set res=new Set();
        while (it_toCut != null) {
            genericNode<Integer> it_notToCut = this.getListe().getFront();

            while (it_notToCut != null) {

                if (it_toCut.getData() == it_notToCut.getData()) {
                    Set n=new Set(it_toCut.getData());
                    res.union(n);
                }
                it_notToCut = it_notToCut.getNext();

            }
            it_toCut = it_toCut.getNext();
        }
        return res;
    }

    @Override
    public Set relativeComplementWith(Set sampleSet) {
        if(sampleSet == null) {
            return null;
        }
        genericNode<Integer> it_toCut = sampleSet.getListe().getFront();

        while (it_toCut != null) {
            genericNode<Integer> it_notToCut = this.getListe().getFront();

            while (it_notToCut != null) {

                if (it_toCut.getData() == it_notToCut.getData()) {
                    this.getListe().remove(it_notToCut.getData());
                }
                it_notToCut = it_notToCut.getNext();

            }
            it_toCut = it_toCut.getNext();
        }
        return this;
    }


    @Override
    public boolean isSubsetOf(Set sampleSet) {
        boolean checker = true;

        genericNode<Integer> it_sample = sampleSet.getListe().getFront();
        while (it_sample != null) {
            if (!this.getListe().ListContains(it_sample.getData())) {
                return false;
            }
            it_sample = it_sample.getNext();
        }
        return checker;
    }

    @Override
    public boolean equals(Set sampleSet) {
        return this.isSubsetOf(sampleSet) && sampleSet.isSubsetOf(this);
    }

    @Override
    public List<Integer> asIntList() {
        return this.liste;
    }

    @Override
    public Iterator<Set> iterator() {
        return new Iterator() {

            genericNode<Integer> node = liste.front;

            @Override
            public boolean hasNext() {

                if (node == null) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Set next() {
                if (hasNext()) {
                    Set n = new Set(node.getData());
                    node = node.getNext();
                    return n;
                } else {
                    return null;
                }
            }
        };
    }

    public void printSet() {
        Iterator<Set> it = this.iterator();
        while (it.hasNext()) {
            Set n = it.next();
            System.out.println(n.getListe().getFront().getData());
        }
    }
}
