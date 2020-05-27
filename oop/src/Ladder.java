public class Ladder {
    private int pointsScored;
    private Team team;
    Ladder(Team team) {
        this.team = team;
    }

    /**
     * Updates the current points
     * @param pointsToAdd number of points to add
     */
    void updatePoints(int pointsToAdd) {
        pointsScored += pointsToAdd;
    }

    /**
     * returns the total points
     * @return
     */
    public int getPointsScored() {
        return pointsScored;
    }
}
