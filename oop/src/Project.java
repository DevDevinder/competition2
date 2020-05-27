//uses team
public class Project {
    private String projectDetails;
    private String projectName;
    private boolean isCompleted;
    private Team assignedTo;
    Project(String projectName, String projectDetails) {
        this.projectName = projectName;
        this.projectDetails = projectDetails;
    }
    Project (Project project) {
        this(project.projectName, project.projectDetails);
    }

    /**
     * Assign this project to the given team
     * @param team team to assign project to
     */
    void assignTo(Team team) {
        assignedTo = team;
        team.assignProject(this);
    }

    /**
     * Returns the project name
     * @return
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Returns boolean value
     * @return
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Completes the project by setting value of isCompleted
     * @param team team that completed this project
     */
    void setCompleted(Team team) {
        if (team.equals(assignedTo)){
            isCompleted = true;
        }else
            throw new RuntimeException("The team cannot complete this project since it is not assigned to them");
    }

    @Override
    public String toString() {
        return String.format("%s - %s", projectName, (isCompleted ? "Completed" : "Not completed"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Project) {
            return projectName.equals(((Project) obj).getProjectName());
        }
        return false;
    }
}
