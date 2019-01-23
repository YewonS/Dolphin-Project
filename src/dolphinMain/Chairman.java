package dolphinMain;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import dolphinMember.*;

public class Chairman implements Manager{

    static int i, j, t, c;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Coach> coachList = Files.reloadCoachFile();
    static Coach coach = new Coach();
    static Coach assignedCoach = new Coach();
    static ArrayList<Member> member = Files.reloadMemberFile();
    static ArrayList<TrainingRecords> trainingRecords = Files.reloadTrainingRecordsFile();
    static ArrayList<CompetitionRecords> competitionRecords = Files.reloadCompetitionRecordsFile();

    public Chairman(){
    }

    @Override
    public void selectedOption(int option) {

        switch(option){
            case 1:
                addNewMember();
                MainScreen.userOption("chairman");
                break;
            case 2:
                modifyMember();
                MainScreen.userOption("chairman");
                break;
            case 3:
                deleteMember();
                MainScreen.userOption("chairman");
                break;
            case 4:
                addNewCoach();
                MainScreen.userOption("chairman");
                break;
            case 5:
                modifyCoach();
                MainScreen.userOption("chairman");
                break;
            case 6:
                deleteCoach();
                MainScreen.userOption("chairman");
                break;
            case 7:
                logout();
                break;
        }
    }

    public void addNewMember(){

        try {

            System.out.println();
            System.out.println("Type in the name: ");
            String name = sc.nextLine();
            System.out.println("Type in the birthday in the format yyyy-mm-dd: ");
            String bday = sc.nextLine();
            LocalDate birthday = LocalDate.parse(bday);
            System.out.println("Which class did the member signed up for: ");
            System.out.println("(Beginner, Intermediate, Advanced, and in case of no classes registered, type in \"none\")");
            String registeredClass = sc.next();
            int[] activity = processActivity(birthday);

            System.out.println("The new member has successfully created and saved.");

            Member newMem = new Member(name, birthday, registeredClass, activity);
            double membershipfee = MembershipFee.calculateMembershipFee(newMem);
            double tuitionfee = MembershipFee.calculateTuitionFee(newMem);
            Member newMemb = new Member(name, birthday, registeredClass, activity, membershipfee, tuitionfee, assignedCoach);

            member.add(newMemb);
            Files.writeMemberToFile(member);

        } catch(InputMismatchException e){
            e.printStackTrace();
            addNewMember();

        }
    }


    public void modifyMember(){

        System.out.println();
        System.out.println("Choose the member to modify: (type in the name)");

        //bring member from the file and print the names
        for(i=0; i<member.size(); i++){
            System.out.println(member.get(i).getName());
        }

        String nameToModify = sc.nextLine();

        for(i=0; i<member.size(); i++){
            if((member.get(i).getName()).equals(nameToModify)){
                j = i;
//            } else {
//                System.out.println("There's no matching member. Try again.");
//                modifyMember();
            }
        }

        for(i=0; i<trainingRecords.size(); i++){
            if((trainingRecords).get(i).getName().equals(nameToModify)){
                t = i;
            }
        }

        for(i=0; i<competitionRecords.size(); i++){
            if((competitionRecords.get(i).getName()).equals(nameToModify)){
                c = i;
            }
        }

        //modify the certain information
        System.out.println("Choose which option to modify: ");
        System.out.println("1. Name \n2. Birthday \n3. class \n4. activity \n5. training records \n6. competition records");
        int modifyOption = sc.nextInt();
        sc.nextLine();

        if(modifyOption < 0 || modifyOption > 7){
            System.out.println("Invalid option. Please try again.");
            modifyMember();
        }

        try {

            switch (modifyOption){
                case 1:
                    System.out.println("Type in the name: ");
                    sc.nextLine();
                    member.get(j).setName(sc.nextLine());
                    System.out.println("The name of the member has successfully modified.");
                    break;

                case 2:
                    System.out.println("Type in the birthday in the format of yyyy-mm-dd");
                    String newBday = sc.nextLine();
                    LocalDate newBirthday = LocalDate.parse(newBday);
                    member.get(j).setBirthday(newBirthday);
                    System.out.println("The birthday of the member has successfully modified.");
                    break;

                case 3:
                    System.out.println("Type in the class: (Beginner, Intermediate, Advanced, and in case of no classes registered, type in \"none\")");
                    member.get(j).setRegisteredClass(sc.nextLine().toLowerCase());
                    System.out.println("The registered class of the member has successfully modified.");
                    break;

                case 4:
//                    Coach currentCoach = member.get(j).getCoach();
//                    String coachName = currentCoach.getName();
//                    for(Coach coach : coachList){
//                        if(coach.getName().equals(coachName)){
//                            int newNumOfSwimmers = coach.getNumOfSwimmers() - 1;
//                            coach.setNumOfSwimmers(newNumOfSwimmers);
//                        }
//                    }

                    int[] modifiedActivity = processActivity(member.get(j).getBirthday());
                    member.get(j).setActivity(modifiedActivity);
                    System.out.println("debug line: chairman switch case: " + member.get(j).getActivity4());
                    System.out.println("The types of activity of the member has successfully modified.");
                    break;

                case 5:
                    //write training records and store them in the file
                    Files.loadTrainingInfo(trainingRecords);
                    System.out.println("Type in the training records: ");
                    System.out.println("Type in the date in the format of 'yyyy-mm-dd' : ");
                    String date = sc.nextLine();
                    LocalDate date1 = LocalDate.parse(date);
                    System.out.println("Type in the ranking(only the number): ");
                    int ranking = sc.nextInt();
                    trainingRecords.get(t).setDate(date1);
                    trainingRecords.get(t).setTrainingResult(ranking);
                    System.out.println("Training records has successfully stored.");
                    break;

                case 6:
                    //write competition records and store them in the file
                    //might also need loadInfo() method for both training records and competition records, to store the initial info and modified info
                    Files.loadCompetitionInfo(competitionRecords);
                    System.out.println("Type in the competition records: ");
                    System.out.println("Type in the time in the format of 'mm:ss' : ");
                    String time = sc.nextLine();
                    System.out.println("Type in the ranking(only the number): ");
                    int ranking1 = sc.nextInt();
                    competitionRecords.get(c).setTime(time);
                    competitionRecords.get(c).setCompetitionResult(ranking1);
                    System.out.println("Competition records has successfully stored.");
                    break;
            }


            System.out.println("debug line: chairman before storing file: " + member.get(j).getActivity4());
            Files.writeMemberToFile(member);
            TreasurerWork.modifyInfo(member.get(j));
            Files.modifyTrainingInfo(member.get(t));
            Files.modifyCompetitionInfo(member.get(c));
            Files.writeTrainingRecordsToFile(trainingRecords);
            Files.writeCompetitionRecordsToFile(competitionRecords);



        } catch(InputMismatchException e){
            e.printStackTrace();
            modifyMember();
        }

    }

    public void deleteMember(){
        try {

            String coachToModify;

            System.out.println();
            System.out.println("Choose the member to delete: (type in the name)");

            //bring member from the file and print the names
            for (i = 0; i < member.size(); i++) {
                System.out.println(member.get(i).getName());
            }

            String nameToDelete = sc.nextLine();

            for (i = 0; i < member.size(); i++) {
                if ((member.get(i).getName()).equals(nameToDelete)) {
                    coachToModify = member.get(i).getCoach().getName();
                    member.remove(i);
                    System.out.println("The chosen member has deleted from the system.");
                    for (i = 0; i < coachList.size(); i++) {
                        if ((coachList.get(i)).equals(coachToModify)) {
                            (coachList.get(i)).setNumOfSwimmers(coachList.get(i).getNumOfSwimmers() - 1);
                        }
                    }
                }
//            else {
//                System.out.println("There's no matching member. Try again.");
//                deleteMember();
            }

            Files.writeMemberToFile(member);
        } catch(InputMismatchException e){
            e.printStackTrace();
            deleteMember();
        }
    }

    public void addNewCoach(){

        try {

            System.out.println();
            System.out.println("Type in the name of the coach: ");
            String name = sc.next();
            System.out.println("Type in the level of the coach: (number 1 for junior, 2 for senior)");
            int levell = sc.nextInt();
            String level;
            if (levell == 1) {
                level = "junior";
            } else {
                level = "senior";
            }
            int numOfSwimmers = 0;

            Coach coach = new Coach(name, level, numOfSwimmers);
            coachList.add(coach);
            Files.writeCoachToFile(coachList);

            System.out.println("The new coach has successfully created and saved.");
        } catch(InputMismatchException e){
            e.printStackTrace();
            addNewMember();
        }

    }

    public void modifyCoach(){

        try {


            int j = 0;

            System.out.println();
            System.out.println("Type in the name of the coach to modify: ");
            for (i = 0; i < coachList.size(); i++) {
                System.out.print(coachList.get(i).getName() + " ");
            }

            System.out.println();
            String coachNameToModify = sc.nextLine();

            for (i = 0; i < coachList.size(); i++) {
                if ((coachList.get(i).getName()).equals(coachNameToModify)) {
                    j = i;
                }
            }

            System.out.println("Choose which information to modify: ");
            System.out.println("1. name \n2. level\n");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Type in the name: ");
                    String name = sc.nextLine();
                    coachList.get(j).setName(name);
                    System.out.println("The name of the coach has successfully modified.");
                    break;
                case 2:
                    System.out.println("Type in the level: (junior or senior)");
                    String level = sc.nextLine();
                    coachList.get(j).setLevel(level.toLowerCase());
                    System.out.println("The level of the coach has successfully modified.");
                    break;
                default:
                    System.out.println("Wrong number input. Please try again.");
                    modifyCoach();
                    break;
            }

            Files.writeCoachToFile(coachList);

        } catch(InputMismatchException e){
            e.printStackTrace();
            modifyCoach();
        }
    }

    public void deleteCoach(){

        try {

            System.out.println();
            System.out.println("Type in the name of the coach to delete: ");
            for (i = 0; i < coachList.size(); i++) {
                System.out.println(coachList.get(i).getName());
            }

            System.out.println();
            String nameToDelete = sc.nextLine();

            for (i = 0; i < coachList.size(); i++) {
                if ((coachList.get(i).getName()).equals(nameToDelete)) {
                    coachList.remove(coachList.get(i));
                }
            }

            System.out.println("The coach has deleted from the system.");
            Files.writeCoachToFile(coachList);

        } catch(InputMismatchException e){
            e.printStackTrace();
            deleteCoach();
        }
    }

    public void logout(){
        MainScreen.homeScreen();
    }

    public int[] processActivity(LocalDate birthday){

        int[] activity = new int[4];

        System.out.println("Choose the type of preferred activity.");
        System.out.println("Type of Membership - Type in 1 for active membership, 2 for passive membership: ");
        activity[0] = sc.nextInt();
        if(activity[0] == 1) {

            LocalDate today = LocalDate.now();
            Period period = Period.between(birthday, today);
            if(period.getYears() < 18) {
                activity[1] = 1;
                System.out.println("The member is junior member.");
            } else {
                activity[1] = 2;
                System.out.println("The member is senior member.");
            }
            System.out.println("Purpose of swimming - Type in 1 for exercise, 2 for elite swimmer");
            activity[2] = sc.nextInt();
            if (activity[2] == 2) {
                if (activity[1] == 1) {
                    System.out.println("Choose the coach for the member: ");
                    coach.availableCoach("junior");

                } else {
                    System.out.println("Choose the coach for the member: ");
                    coach.availableCoach("senior");
                }

                sc.nextLine();
                String coachName = sc.nextLine();

                changeNumOfSwimmers();

                for (i = 0; i < coachList.size(); i++) {
                    if (((coachList.get(i)).getName()).equals(coachName)) {
                        assignedCoach = coachList.get(i);
                        //store the changes in the information
                        assignedCoach = new Coach(assignedCoach.getName(), assignedCoach.getLevel(), assignedCoach.getNumOfSwimmers()+1);
                        coachList.set(i, assignedCoach);
                        //write to the coach file so that it reflects the changes in number of swimmers
                    }
                }

                for(Member member: member){
                    if(member.getBirthday() == birthday){
                        member.setCoach(assignedCoach);
                    }
                }
            }

        } else{
            activity[1] = 0;
            activity[2] = 0;
        }

        System.out.println("What is the swimming discipline: ");
        System.out.println("(type in 1 for freestyle, 2 for backstroke, 3 for breaststroke, 4 for butterfly, 5 for medley)");
        int discipline = sc.nextInt();
        activity[3] = discipline;

        Files.writeCoachToFile(coachList);
        return activity;
    }

    public ArrayList<Coach> changeNumOfSwimmers(){
        //hashmap
        //get the names, count, and then save it, instead of all the +1, -1 thingy

        HashMap<String, Integer> coachCount = new HashMap<>();
        int count = 0;

        for(Coach coaches : coachList){
            String coachName = coaches.getName();
            for(Member members : member){
                if(members.getCoach().getName().equals(coachName)){
                    count ++;
                    coachCount.put(coachName, count);
                }
            }

            coaches.setNumOfSwimmers(count);
            count = 0;
        }

        return coachList;
    }

}
