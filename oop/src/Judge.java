
//Inherits from teacher
public class Judge extends Teacher {
    Judge(String name, String college) {
        super(name, college);
    }

    /**
     * Awards given points to the given team
     * @param points points to award
     * @param team team to award points to
     */
    void awardPoints(int points, Team team) {
        team.getLadder().updatePoints(points);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Checks if this judge is equal to the given object
     * @param obj object to compare with
     * @return boolean value
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Judge) {
            return getName().equals(((Judge) obj).getName());
        } else if (obj instanceof Teacher) {
            return getName().equals(((Teacher) obj).getName());
        } else return false;
    }
}
