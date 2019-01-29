package dolphinMain;

import dolphinMember.Files;

import java.util.ArrayList;

/**
 * Coach class is for instantiating object Coach.
 * It also can check for the available coach when registering member in the system.
 */

public class Coach extends Authorized implements Manager {

    private String name;
    private String level;
    private int numOfSwimmers;

    public Coach(){
    }

    public Coach(String name){
        this.name = name;
    }
    public Coach(String name, String level, int numOfSwimmers){
        this.name = name;
        this.level = level;
        this.numOfSwimmers = numOfSwimmers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getNumOfSwimmers() {
        return numOfSwimmers;
    }

    public void setNumOfSwimmers(int numOfSwimmers) {
        this.numOfSwimmers = numOfSwimmers;
    }

    /**
     * Goes through the coaches and checks for the available coaches
     * @param level the level that the coach is teaching
     */
    public void availableCoach(String level){

        //reloading coaches from the file
        ArrayList<Coach> coachList = Files.reloadCoachFile();

        //checking available coaches
        int i;
        ArrayList<String> availableCoach = new ArrayList<>();

        for(i=0; i<coachList.size(); i++) {
            if ((coachList.get(i)).getLevel().equals(level)) {
                if ((coachList.get(i)).getNumOfSwimmers() < 5) {
                    availableCoach.add((coachList.get(i).getName()));
                }
            }
        }

        System.out.println("The available coaches are ");
        for(String coachable : availableCoach)
        {
            System.out.print(coachable + " ");
        }
        System.out.print("\n");

    }

    /**
     * Calls options that the coach selected
     * @param option the option that the coach chose
     */
    @Override
    public void selectedOption(int option) {

        switch (option) {
            case 1:
                viewResults();
                MainScreen.userOption("coach");
                break;
            case 2:
                viewTop5();
                MainScreen.userOption("coach");
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                MainScreen.userOption("coach");
                break;

        }
    }

    /**
     * Log out and go back to log in screen
     */
    public void logout(){
        MainScreen.homeScreen();
    }
}
