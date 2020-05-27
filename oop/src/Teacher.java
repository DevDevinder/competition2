import java.util.ArrayList;
//inherits from person
public class Teacher extends Person{
    private static ArrayList<Teacher> listOfTeachers;
    static {
        listOfTeachers = new ArrayList<>();
    }
    Teacher(String name, String college) {
        setName(name);
        setCollege(college);
        listOfTeachers.add(this);
    }

    public static ArrayList<Teacher> getListOfTeachers() {
        return listOfTeachers;
    }

    public static int getNumberOfTeachers() {
        return listOfTeachers.size();
    }

    @Override
    public String toString() {
        return getName();
    }
}
