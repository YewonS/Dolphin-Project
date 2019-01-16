package dolphinMain;

import dolphinMember.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Authorized {

    static Scanner sc = new Scanner(System.in);
    static Member member = new Member();
    static ArrayList<Member> memberList = Files.reloadMemberFile();
    static int i = 0;

    public void viewAllMembers(){
        System.out.println();
        for(i=0; i<memberList.size(); i++){
            member = memberList.get(i);
            System.out.println(member.getName() + ", " + member.getBirthday() + ", " + member.getRegisteredClass() + ", " + member.getActivity1() + ", " + member.getActivity2() + ", " + member.getActivity3() + ", " + member.getMembershipFee() + ", " + member.getTuitionFee() + member.getCoach());
        }
    }

    public static void viewResults(){
        System.out.println();
        System.out.println("Choose which records to load: ");
        System.out.println("number 1 for training records and number 2 for competition records");
        int option = sc.nextInt();

        switch(option){
            case 1:
                ArrayList<TrainingRecords> trainingRecords = Files.reloadTrainingRecordsFile();
                for(i=0; i<trainingRecords.size(); i++) {
                    TrainingRecords trainingRecords1 = trainingRecords.get(i);
                    System.out.println(trainingRecords1.getName() + ", " + trainingRecords1.getDate() + ", " + trainingRecords1.getTrainingResult());
                }
                break;
            case 2:
                ArrayList<CompetitionRecords> competitionRecords = Files.reloadCompetitionRecordsFile();
                for(i=0; i<competitionRecords.size(); i++) {
                    CompetitionRecords competitionRecords1 = competitionRecords.get(i);
                    System.out.println(competitionRecords1.getName() + ", " + competitionRecords1.getTime() + ", " + competitionRecords1.getCompetitionResult());
                }
                break;
            default:
                System.out.println("Invalid number input. Please try again.");
                viewResults();
                break;
        }
    }

    public static void viewTop5(){
        System.out.println();
        System.out.println("Choose which records to load: ");
        System.out.println("number 1 for training records and number 2 for competition records");
        int option = sc.nextInt();

        //todo: as for training top 5, view the result top 5 according to the time periods in a month.
        //todo: as for competition top 5, print out the top 5 according to the shortest time span.
    }
}
