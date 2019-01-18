package dolphinMain;

import dolphinMember.*;

import java.time.LocalTime;
import java.util.*;

public class Authorized {

    static Scanner sc = new Scanner(System.in);
    static Member member = new Member();
    static ArrayList<Member> memberList = Files.reloadMemberFile();
    static ArrayList<TrainingRecords> trainingRecords = Files.reloadTrainingRecordsFile();
    static ArrayList<CompetitionRecords> competitionRecords = Files.reloadCompetitionRecordsFile();
    ArrayList<CompetitionRecords> newCompRecords = new ArrayList<>();
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

    public void viewTop5(){

        try {
            System.out.println();
            System.out.println("Choose which records to load: ");
            System.out.println("number 1 for training records and number 2 for competition records");
            int option = sc.nextInt();

            if (option == 1) {
                System.out.println("Type in the month you'd like to check the results: ");
                int month = sc.nextInt();
                for(TrainingRecords trRecord : trainingRecords){
                    int trMonth = trRecord.getDate().getMonthValue();
                    if(trMonth == month) {
                        if (trRecord.getTrainingResult() > 0 && trRecord.getTrainingResult() < 6) {
                            System.out.println("Name: " + trRecord.getName() + ", Discipline: " + trRecord.getDiscipline() + ", Date: " + trRecord.getDate().toString() + ", Training Record: " + trRecord.getTrainingResult());
                        }
                    } else {
                        System.out.println("Invalid month. Please try again.");
                        viewTop5();
                    }
                }
            } else if (option == 2) {
                for(CompetitionRecords compRecord : competitionRecords){
                    LocalTime compTime = LocalTime.parse(compRecord.getTime());
                    if(!compTime.toString().equals("00:00")){
                        newCompRecords.add(new CompetitionRecords(compRecord.getName(), compRecord.getDiscipline(), compTime, compRecord.getCompetitionResult()));
                    }
                }

                for(i=0; i<newCompRecords.size(); i++){
                        Collections.sort(newCompRecords, new CustomComparator());
                }

                for(i=0; i<5; i++){
                    System.out.println("Name: " + newCompRecords.get(i).getName() + ", Discipline: " + newCompRecords.get(i).getDiscipline() + ", Time: " + newCompRecords.get(i).getTime1().toString() + ", Competition Result: " + newCompRecords.get(i).getCompetitionResult());
                }

            } else {
                System.out.println("Invalid input. Please try again.");
                viewTop5();
            }

        } catch(InputMismatchException e){
            e.printStackTrace();
            viewTop5();
        }
    }
}


class CustomComparator implements Comparator<CompetitionRecords> {
    @Override
    public int compare(CompetitionRecords c1, CompetitionRecords c2) {
        return c1.getTime1().compareTo(c2.getTime1());
    }
}