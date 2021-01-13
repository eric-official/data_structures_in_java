// Abgabe von Eric Näser (Matrikel: 524319) und Jermaine Merkert (Matrikel: 535322)
package PVL5_Group14;

public interface InterfaceSet extends Iterable{
    Set union(Set toInsert);
    Set cut(Set toCutWith);
    Set relativeComplementWith(Set sampleSet);
    boolean isSubsetOf(Set sampleSet);
    boolean equals(Set sampleSet);
    List<Integer> asIntList();
}
