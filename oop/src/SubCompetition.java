import java.util.ArrayList;
//uses project and judges
public class SubCompetition {
    private ArrayList<Judge> judges;
    private Project project;
    private int maxPointReward;
    SubCompetition (Project project, int maxPointReward) {
        this.project = project;
        judges = new ArrayList<>();
        if (maxPointReward < 0) {
            throw new RuntimeException("Max point reward cannot be negative");
        }
        this.maxPointReward = maxPointReward;
    }

    /**
     * Return the list of judges of this sub competition
     * @return
     */
    public ArrayList<Judge> getJudges() {
        return judges;
    }

    /**
     * Assign a list of judge for this sub competition
     * @param judges
     */
    public void assignJudges(ArrayList<Judge> judges) {
        this.judges = judges;
    }

    /**
     * Adds a judge for this sub competition
     * @param judge
     */
    public void addJudge(Judge judge) {
        this.judges.add(judge);
    }

    /**
     * Returns the project for this sub competition
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     * returns the max point that can be awarded by completing this sub competition
     * @return
     */
    public int getMaxPointReward() {
        return maxPointReward;
    }

    /**
     * Returns true if the given Teacher is a judge of the competition
     * @param teacher teacher
     * @return
     */
    public boolean isJudgeInCompetition(Teacher teacher) {
        return judges.contains(teacher);
    }

}
