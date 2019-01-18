package dolphinMain;

import java.util.*;

public class MainScreen {

    static Scanner sc = new Scanner(System.in);

    public static void homeScreen(){

        System.out.println();
        System.out.print(
         " \n" +
                 "                                   __   \n" +
                 "                               _.-~  )  \n" +
                 "                    _..--~~~~,'   ,-/     _  \n" +
                 "                 .-'. . . .'   ,-','    ,' ) \n" +
                 "              ,'. . .  _  ,--~,-'  _..-' ,'  \n" +
                 "           , '. . . . (@) '---~~~~    ,'     \n" +
                 "          /. . . .   '~~          ,-'         \n" +
                 "         /. . . . .            ,-'           \n" +
                 "        ; . . . .  - .       ,'      \n" +
                 "       : . . . .       _    /       \n" +
                 "       . . . . .          `-.:       \n" +
                 "      . . . ./  - .           )       \n" +
                 "      .  . .|   _____..---.._/ _____   \n" +
                 "     ~---~~~~----~~~~             ~~  \n"
        );
        System.out.println("----------------------------------------------------------");
        System.out.println("    Welcome to Dolphin Swimming Club Management System. ");
        System.out.println("----------------------------------------------------------");

        System.out.println();
        System.out.println("Please type in your username and password to log in.");
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();

        User newUser = new User(username, password);

        if(validateUser(newUser)){
            String userLoggedIn = (newUser.getUsername()).toLowerCase();
            userOption(userLoggedIn);
        } else {
            System.out.println("Wrong username and password. Please try again.");
            homeScreen();
        }

    }

    public static boolean validateUser(User newUser){

        String users[] = new String[] {"chairman", "treasurer", "coach"};

        for(String user : users) {
            if ((newUser.getUsername()).equals(user))
                if((newUser.getUsername()).equals(newUser.getPassword()))
                return true;
        }

        return false;
    }

    public static void userOption(String loggedInUser){

        int i;
        String[] chairmanOptions = new String[] {"Add new member", "Modify member", "Delete member", "Add coach", "Modify coach", "Delete coach", "log out"};
        String[] treasurerOptions = new String[] {"Add payments", "Cancel payments", "View members behind payment", "log out"};
        String[] coachOptions = new String[] {"View results", "View top 5 members", "log out"};

        System.out.println("Choose the option: ");

        if(loggedInUser.equals("chairman")){
            for(i=1; i<chairmanOptions.length+1; i++) {
                System.out.println(i + ". " + chairmanOptions[i-1]);
            }

            int chairmanOption = sc.nextInt();
            Chairman chairman = new Chairman();
            chairman.selectedOption(chairmanOption);

        } else if(loggedInUser.equals("treasurer")){
            for(i=1; i<treasurerOptions.length+1; i++) {
                System.out.println(i + ". " + treasurerOptions[i-1]);
            }

            int treasurerOption = sc.nextInt();
            Treasurer treasurer = new Treasurer();
            treasurer.selectedOption(treasurerOption);

        } else if(loggedInUser.equals("coach")){
            for(i=1; i<coachOptions.length+1; i++) {
                System.out.println(i + ". " + coachOptions[i-1]);
            }

            int coachOption = sc.nextInt();
            Coach coach = new Coach();
            coach.selectedOption(coachOption);
        }

    }

}
