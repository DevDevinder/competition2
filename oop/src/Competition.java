import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//Uses Team, SubCompetition, Project
public class Competition {
    private ArrayList<SubCompetition> subCompetitions;
    private ArrayList<Team> teamsParticipating;
    Competition(SubCompetition... subCompetitions) {
        this.subCompetitions = new ArrayList<>(Arrays.asList(subCompetitions));
        teamsParticipating = new ArrayList<>();
    }

    /**
     * Returns the sub competitions of this competition
     * @return sub competitions
     */
    public ArrayList<SubCompetition> getSubCompetitions() {
        return subCompetitions;
    }

    /**
     * Returns a string containing the final ranking if the competition is finished
     * @return final rankings
     */
    public String viewFinalRanking() {
        ArrayList<Team> toSort = new ArrayList<>();
        for (Team team : teamsParticipating) {
            for (Project project: team.getProjects()) {
                if (!project.isCompleted()) {
                    return "Competition is still on going";
                }
            }
            toSort.add(team);
        }
        toSort.sort(Comparator.comparingInt(o -> o.getLadder().getPointsScored()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("RANKINGS:\nn");
        for (int i = 1; i <= toSort.size();i++) {
            stringBuilder.append(String.format("\t%d) %s\n", i, toSort.get(i-1)));
        }
        return stringBuilder.toString();
    }

    /**
     * Returns a string containing the
     * @return
     */
    public String viewProjects() {
        if (teamsParticipating.size() != 0) {
            String result = "";
            for (Team team : teamsParticipating) {
                result += team.getTeamProgress();
            }
            return result;
        } else
        return "No teams registered in any competition";
    }

    /**
     * Adds a team to this competition
     * @param team team to add
     */
    public void addTeam(Team team) {
        teamsParticipating.add(team);
        for (SubCompetition subCompetition : subCompetitions) {
            Project project1 = new Project(subCompetition.getProject());
            project1.assignTo(team);
        }

    }

    /**
     * Returns the teams participating in this competition
     * @return teams participating
     */
    public ArrayList<Team> getTeamsParticipating() {
        return teamsParticipating;
    }




}
