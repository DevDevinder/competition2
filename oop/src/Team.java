import java.util.ArrayList;
//uses Student, Project, and Teacher
public class Team {
    private String name; //Name of the college
    private Teacher coach; //coach of the team
    private Ladder ladder; //current points of the team
    private ArrayList<Student> students; //list of students in the team -- limited to 4
    private ArrayList<Project> projects; //list of project assigned to the team
    private static ArrayList<Team> listOfTeams;


    static {
        listOfTeams = new ArrayList<>();
    }

    /**
     * Creates a team given name, list of students, and coach
     * @param name name of the college
     * @param students roster of students
     * @param coach teacher that acts a coach for the students
     */
    Team(String name, ArrayList<Student> students, Teacher coach) {
        this.name = name;
        if (students.size() != 4) {
            throw new RuntimeException("There must be exactly 4 students in a team");
        }
        this.students = students;
        this.coach = coach;
        ladder = new Ladder(this);
        projects = new ArrayList<>();
        listOfTeams.add(this);
    }

    /**
     * returns the list of teams
     * @return teams
     */
    public static ArrayList<Team> getListOfTeams() {
        return listOfTeams;
    }

    /**
     * Checks if the student given is not in a team
     * @param student student to check
     * @return boolean
     */
    public static boolean isStudentNotInATeam(Student student) {
        for (Team team : listOfTeams) {
            if (team.getStudents().contains(student))
                return false;
        }
        return true;
    }

    /**
     * returns the list of students in the team
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Returns a string representing the progress of the team for the judges to view
     * @return string
     */
    public String getTeamProgress() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toString()).append("\n");
        for (Project project : projects){
            stringBuilder.append("\t").append(project.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string containing the name of the college, students, and coach of the team
     * @return string
     */
    public String getTeamDetails() {
        return String.format("College :%s\n\tStudents(%s, %s, %s, %s)\n\tCoach:%s\n", name, students.get(0), students.get(1), students.get(2), students.get(3), coach);
    }

    /**
     * Returns the list of projects assigned to the team
     * @return list of projects
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Returns a string containing the information of the team
     * @return
     */
    public String teamDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTeamDetails());
        stringBuilder.append("Projects Status: ");
        for (Project project: projects) {
            stringBuilder.append(project.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Returns the coach of the team
     * @return
     */
    Teacher getCoach() {
        return coach;
    }

    /**
     * Returns the points of the team
     * @return current points in the ladder
     */
    Ladder getLadder() {
        return ladder;
    }

    /**
     * Assigns a given project to the team
     * @param project project to assign the team to
     */
    void assignProject(Project project) {
        projects.add(project);
    }

    /**
     * Completes the given project of the team
     * @param project project to complete
     */
    void completeProject(Project project) {
        if (projects.contains(project)) {
            project.setCompleted(this);
        }
    }
}
