import java.awt.*;
import java.util.ArrayList;

//uses Teacher and Judge
public class CompetitionDatabase {
    static class Pair {
        String username;
        String password;
        Pair(String username, String password){
            this.username = username;
            this.password = password;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pair) {
                return username.equals(((Pair) obj).username) && password.equals(((Pair) obj).password);
            }
            return false;
        }
    }
    private static ArrayList<Pair> usernameAndPasswords;
    private static ArrayList<Teacher> coaches;
    private static ArrayList<Judge> listOfJudges;

    static {
        usernameAndPasswords = new ArrayList<>();
        coaches = new ArrayList<>();
        listOfJudges = new ArrayList<>();
    }

    /**
     * Adds a judge for the competition
     * @param judge judge to add
     */
    static void addJudge(Judge judge) {
        listOfJudges.add(judge);
    }

    /**
     * Returns the list of judges chosen for the competition
     * @return list of judges
     */
    public static ArrayList<Judge> getListOfJudges() {
        return listOfJudges;
    }

    /**
     * Registers teacher given username and password
     * @param teacher teacher registering as coach
     * @param competitionUsername username of coach
     * @param competitionPassword password of coach
     * @return true if successful
     */
    static boolean register(Teacher teacher, String competitionUsername, String competitionPassword) {
        if (coaches.contains(teacher)) {
            return false;
        }
        coaches.add(teacher);
        usernameAndPasswords.add(new Pair(competitionUsername, competitionPassword));
        return true;
    }

    /**
     * Finds the coach given username and password
     * @param username username of the coach
     * @param password password of the coach
     * @return a teacher
     */
    static Teacher findTeacherWithUsernameAndPassword(String username, String password) {
        for (int i =0; i < usernameAndPasswords.size(); i++) {
            if (usernameAndPasswords.get(i).equals(new Pair(username, password)))
                return coaches.get(i);
        }
        return null;
    }
}
