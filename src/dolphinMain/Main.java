package dolphinMain;

import dolphinMember.*;

/**
 * <h1>Dolphin Swimming Club Management System<h1/>
 * This management system is for Dolphin Swimming Club.
 * As a developer of the system, I tried to implement many functions and features
 * so that the program makes managing the members of swimming club easier, adaptable, flexible, and secure.
 * This program helps the chairman to manage the members,
 * treasurer to manage matters regarding the fees, and coaches to help understand performances of members.
 *
 * @author Yewon Seo
 * @version 1.0
 */

public class Main {

    public static void main(String [] args){

        Files.createFile();
        MainScreen.homeScreen();

    }

}
