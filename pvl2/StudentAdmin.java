//Abgabe Eric Näser (Matrikel: 524319)
package PVL2_Group14;

public class StudentAdmin {
    public interface StudentAdministration {
        Integer matriculate(String firstName, String lastName);
        Boolean deregister(Integer matriculationNumber);
        String find(Integer matriculationNumber);
        String takeExam(Integer matriculationNumber, String courseID, Boolean passed);
        String dataBase();
    }
}
