import java.util.ArrayList;
//inherits from person
public class Student extends Person{
    private String idNumber;
    private String address;
    private String telephone;
    private String status;
    private static ArrayList<Student> listOfStudents;

    static {
        listOfStudents = new ArrayList<>();
    }

    Student(String idNumber, String address, String telephone, String status) {
        this.idNumber = idNumber;
        this.address = address;
        this.telephone = telephone;
        this.status = status;
        listOfStudents.add(this);
    }

    /**
     * Returns the list of students
     * @return list of students
     */
    public static ArrayList<Student> getListOfStudents() {
        return listOfStudents;
    }

    @Override
    public String toString() {
        return idNumber;
    }

    @Override
    public int hashCode() {
        return idNumber.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return idNumber.equals(((Student) obj).idNumber);
        }
        return false;
    }
}
