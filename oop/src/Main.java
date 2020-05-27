import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


//uses Competition, CompetitionDatabase, Judge, Ladder, Project, Student, SubCompetition, Teacher, Team
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {
        String[][] listOfTeacherString = new String[][] {
                {"Teacher of ABC", "ABC"},
                {"Teacher of DEF", "DEF"},
                {"Teacher of GHI", "GHI"},
                {"Teacher of JKL", "JKL"},
                {"Teacher of ABC2", "ABC"},
                {"Teacher of DEF2", "DEF"},
                {"Teacher of GHI2", "GHI"},
                {"Teacher of JKL2", "JKL"},
        };

        String[][] listOfJudges = new String[][] {
                {"Judge from ABC", "ABC"},
                {"Judge from DEF", "DEF"},
                {"Judge from GHI", "GHI"},
                {"Judge from JKL", "JKL"},
        };
        for (String[] teacher : listOfTeacherString) {
            new Teacher(teacher[0], teacher[1]);
        }
        for (String[] judge : listOfJudges) {
            CompetitionDatabase.addJudge(new Judge(judge[0], judge[1]));
        }


        SubCompetition softwareDevelopmentCompetition = new SubCompetition(new Project("Software Develpoment", "details"), 10);
        SubCompetition networkingCompetition = new SubCompetition(new Project("Networking", "details"), 10);
        SubCompetition webDesignCompetition = new SubCompetition(new Project("Web Design", "details"), 10);
        Competition competition = new Competition(softwareDevelopmentCompetition, networkingCompetition, webDesignCompetition);


        //default teachers/judges/coaches/teams
        CompetitionDatabase.register(Teacher.getListOfTeachers().get(0), "user", "pass");
        softwareDevelopmentCompetition.addJudge(CompetitionDatabase.getListOfJudges().get(0));
        networkingCompetition.addJudge(CompetitionDatabase.getListOfJudges().get(1));
        webDesignCompetition.addJudge(CompetitionDatabase.getListOfJudges().get(2));
        Team teamTemp = new Team(Teacher.getListOfTeachers().get(0).getCollege(), importStudents("students_abc_college.csv"), Teacher.getListOfTeachers().get(0));
        competition.addTeam(teamTemp);

        String[] actAs = new String[]{
                "Public",
                "Teacher",
                "Coach",
                "Team",
                "Judge",
                "Exit"
        };
        String[] publicActions = new String[]{
                "View all competition details",
                "View all Software Development Competition details",
                "View all Networking Competition details",
                "View all Web Design Competition details",
                "View final rankings",
                "Back"
        };

        String[] teacherActions = new String[]{
                "Register to competition",
                "Back"
        };

        String[] teamActions = new String[]{
                "Complete project",
                "View projects",
                "View points",
                "Back"
        };

        String[] judgeActions = new String[]{
                "View details of the competition",
                "Award points to a team",
                "Back"
        };

        //The program
        while (true) {
            printOptions("Act As", actAs);
            switch (getChoice(1, actAs.length)) {
                case 1:
                    printOptions("Public actions:", publicActions);
                    switch (getChoice(1, publicActions.length)) {
                        case 1:
                            System.out.println(softwareDevelopmentCompetition.toString());
                            System.out.println(networkingCompetition.toString());
                            System.out.println(webDesignCompetition.toString());
                            break;
                        case 2:
                            System.out.println(softwareDevelopmentCompetition.toString());
                            break;
                        case 3:
                            System.out.println(networkingCompetition.toString());
                            break;
                        case 4:
                            System.out.println(webDesignCompetition.toString());
                            break;
                        case 5:
                            System.out.println(competition.viewFinalRanking());
                    }
                    break;
                case 2:
                    printOptions("Act as which teacher", Teacher.getListOfTeachers());
                    int teacher = getChoice(1, Teacher.getNumberOfTeachers());
                    printOptions("Teacher actions", teacherActions);
                    int inputTeacher = getChoice(1, teacherActions.length);
                    Teacher teacher1 = Teacher.getListOfTeachers().get(teacher-1);
                    switch (inputTeacher) {
                        case 1:
                            System.out.print("Enter username to use: ");
                            String username =getStringInput();
                            System.out.print("Enter password to use: ");
                            String password = getStringInput();
                            if (CompetitionDatabase.register(teacher1, username, password)) {
                                System.out.printf("%s is now a coach and registered to the system.%n", teacher1.getName());
                            }else {
                                System.out.printf("%s is already registered!\n", teacher1.getName());
                            }
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 3:
                    System.out.print("Enter username to use: ");
                    String username =getStringInput();
                    System.out.print("Enter password to use: ");
                    String password = getStringInput();

                    Teacher teacher2 = CompetitionDatabase.findTeacherWithUsernameAndPassword(username, password);
                    if (teacher2 != null) {
                        System.out.printf("Logged in as %s%n", teacher2.getName());
                        System.out.print("Enter csv filename containing student information: ");
                        String string = getStringInput();
                        ArrayList<Student> students = importStudents(string);
                        Team team = new Team(teacher2.getCollege(), students, teacher2);
                        competition.addTeam(team);
                    } else {
                        System.out.println("Incorrect username or password");
                    }

                    break;
                case 4:
                    if (Team.getListOfTeams().size() == 0) {
                        System.out.println("No teams created yet!");
                        break;
                    }
                    printOptions("Act as which team", Team.getListOfTeams());
                    int teamIndex = getChoice(1, Team.getListOfTeams().size());
                    printOptions("Team actions", teamActions);
                    int teamAction = getChoice(1, teamActions.length);
                    Team team = Team.getListOfTeams().get(teamIndex-1);
                    switch (teamAction) {
                        case 1:
                            printOptions("Choose which project to complete", team.getProjects());
                            int projectToCompleteIndex = getChoice(1, team.getProjects().size());
                            team.getProjects().get(projectToCompleteIndex-1).setCompleted(team);
                            break;
                        case 2:
                            printOptions("List of projects", team.getProjects());
                            break;
                        case 3:
                            System.out.printf("Current points: %d%n", team.getLadder().getPointsScored());
                    }
                    break;

                case 5:
                    printOptions("Act as which judge", CompetitionDatabase.getListOfJudges());
                    int judgeIndex = getChoice(1, CompetitionDatabase.getListOfJudges().size());
                    Judge judge = CompetitionDatabase.getListOfJudges().get(judgeIndex-1);
                    //ArrayList<Project> projectJudging = new ArrayList<>();
                    for (SubCompetition subCompetition : competition.getSubCompetitions()) {
                        if (subCompetition.getJudges().contains(judge)) {
                            System.out.println("|| Judging for " + subCompetition.getProject().getProjectName() + " ||");
                            //projectJudging.add(subCompetition.getProject());
                        }
                    }
                    printOptions("Judge actions", judgeActions);
                    int inputJudge = getChoice(1, judgeActions.length);
                    switch (inputJudge) {
                        case 1:
                            System.out.println(competition.viewProjects());
                            break;
                        case 2:
                            printOptions("Team to award points", Team.getListOfTeams());
                            Team team1 = Team.getListOfTeams().get(getChoice(1, Team.getListOfTeams().size())-1);
                            System.out.print("Amount of points to award: ");
                            int points = getIntegerInput(0, 10);

                            for (SubCompetition subCompetition : competition.getSubCompetitions()) {
                                if (subCompetition.getJudges().contains(judge)) {
                                    for (Project project : team1.getProjects()) {
                                        if (subCompetition.getProject().equals(project)) {
                                            if (project.isCompleted()) {
                                                judge.awardPoints(points, team1);
                                                System.out.println("Points Awarded!");
                                                break;
                                            } else {
                                                System.out.printf("%s can't award point to %s since their project is not done yet\n", judge.getName(), team1.toString());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            System.out.printf("%s can't award point to %s\n", judge.getName(), team1.toString());
                    }
                    break;
                case 6:

                    System.exit(0);
            }
            System.out.print("Enter any key to continue...");
            scanner.nextLine();
        }

    }

    static ArrayList<Student> importStudents(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<Student> students = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            String[] line = temp.split(",");
            if (line.length != 4)
                throw new RuntimeException(String.format("Line: %s %nInvalid students csv file!", temp));
            else {
                students.add(new Student(line[0], line[1], line[2], line[3]));
            }
        }
        return students;
    }

    static void printOptions(String title, Object[] options) {
        System.out.println(title);
        for (int i = 1; i <= options.length; i++) {
            System.out.printf("%d) %s%n", i, options[i-1].toString());
        }
    }

    static void printOptions(String title, ArrayList options) {
        printOptions(title, options.toArray());
    }

    static Integer getChoice(int min, int max) {
        System.out.print("Enter choice: ");
        return getIntegerInput(min, max);
    }

    static Integer getIntegerInput(int min, int max) {
        while (true) {
            try {
                int inte = Integer.parseInt(scanner.nextLine());
                if (inte < min || inte > max)
                    System.out.printf("Must number greater than %d or less than %d! Enter again:",min, max);
                else {
                    return inte;
                }
            } catch (NumberFormatException e) {
                System.out.print("Must input integer! Enter again:");
            }
        }
    }
    static String getStringInput() {
        return scanner.nextLine();
    }

}
