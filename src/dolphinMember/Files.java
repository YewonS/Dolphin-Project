package dolphinMember;

import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.String;
import java.io.*;
import dolphinMain.*;

public class Files {

    static ArrayList<Member> member = new ArrayList<>();
    static ArrayList<Coach> coach = new ArrayList();
    static ArrayList<Treasurer> treasurer = new ArrayList<>();
    static ArrayList<TrainingRecords> trainingRecords = new ArrayList<>();
    static ArrayList<CompetitionRecords> competitionRecords = new ArrayList<>();
    static int activity[] = new int[3];

    public static void createFile(){
        try {
            new File("TrainingRecords.csv").createNewFile();
            new File("CompetitionRecords.csv").createNewFile();
            new File("Members.csv").createNewFile();
            new File("Coach.csv").createNewFile();
            new File("Treasurer.csv").createNewFile();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    //writing to files

    public static void writeMemberToFile(ArrayList<Member> members){

        //todo: find a way to align the information in order: active, junior, elite - active, junior, exercise - active, senior, elite - active, senior, exercise - passive.
        try{
            PrintWriter pw = new PrintWriter(new File("Members.csv"));
            StringBuilder sb = new StringBuilder();

            pw.write("name,age,registered class,type of membership,type of swimmer,purpose,membership fee,tuition fee,coach\n");
            for (int i = 0; i < members.size(); i++) {
                Member memberFile = members.get(i);
                System.out.println(memberFile.getName() + " has added to the file.");
                sb.append(memberFile.getName() + ",");
                sb.append(memberFile.getBirthday()+",");
                sb.append(memberFile.getRegisteredClass()+",");
                sb.append(memberFile.getActivity1()+",");
                sb.append(memberFile.getActivity2()+",");
                sb.append(memberFile.getActivity3()+",");
                sb.append(memberFile.getMembershipFee()+",");
                sb.append(memberFile.getTuitionFee()+",");
                sb.append(memberFile.getCoach().getName());
                sb.append('\n');
            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void writeCoachToFile(ArrayList<Coach> coaches){
        //todo: align the coaches, junior first and then senior

        try{
            PrintWriter pw = new PrintWriter(new File("Coach.csv"));
            StringBuilder sb = new StringBuilder();

            pw.write("name,level,number of swimmers\n");
            for (int i = 0; i < coaches.size(); i++) {
                Coach coachFile = coaches.get(i);

                sb.append(coachFile.getName() + ",");
                sb.append(coachFile.getLevel()+",");
                sb.append(coachFile.getNumOfSwimmers());
                sb.append('\n');
            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void writeTreasurerFile(ArrayList<Treasurer> treasurers){
        //todo: align the information in the following order: both fees paid first, one fee paid, and then no fees paid.
        PrintWriter pw;
        try {
            pw = new PrintWriter(new File("Treasurer.csv"));
            StringBuilder sb = new StringBuilder();

            pw.write("name,membership fee,membership fee paid,tuition fee,tuition fee paid\n");
            for(int i = 0; i < treasurers.size(); i++){
                Treasurer treasurerFile = treasurers.get(i);

                sb.append(treasurerFile.getName() + ",");
                sb.append(treasurerFile.getMembershipFee() + ",");
                sb.append(treasurerFile.isMembPaid());
                sb.append(treasurerFile.getTuitionFee() + ",");
                sb.append(treasurerFile.isTuitPaid());
                sb.append('\n');

            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeTrainingRecordsToFile(ArrayList<TrainingRecords> trainingRecords){

        //todo: align the information in the following order: junior, higher result first and then senior, higher results.

        PrintWriter pw;
        try {
            pw = new PrintWriter(new File("TrainingRecords.csv"));
            StringBuilder sb = new StringBuilder();

            pw.write("name,date,training result\n");
            for(int i = 0; i < trainingRecords.size(); i++){
                TrainingRecords trRecordFile = trainingRecords.get(i);

                sb.append(trRecordFile.getName() + ",");
                sb.append(trRecordFile.getDate() + ",");
                sb.append(trRecordFile.getTrainingResult());
                sb.append('\n');

            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeCompetitionRecordsToFile(ArrayList<CompetitionRecords> competitionRecords){
        PrintWriter pw;
        try {
            pw = new PrintWriter(new File("CompetitionRecords.csv"));
            StringBuilder sb = new StringBuilder();

            pw.write("name,time,competition result\n");
            for(int i = 0; i < competitionRecords.size(); i++){
                CompetitionRecords compRecordFile = competitionRecords.get(i);

                sb.append(compRecordFile.getName()+ ",");
                sb.append(compRecordFile.getTime()+ ",");
                sb.append(compRecordFile.getCompetitionResult());
                sb.append('\n');

            }

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //reloading files

//    public static void reloadMFile(String nameOfFile){
//        String filename = nameOfFile.toLowerCase();
//        switch(filename){
//            case "member":
//                reloadMemberFile();
//                break;
//            case "result":
//                reloadRecordsFile();
//                break;
//            case "coach":
//                reloadCoachFile();
//                break;
//        }
//    }

    public static ArrayList<Member> reloadMemberFile(){
        try{
            member.clear();

            File file = new File("Members.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] reloadFile = line.split(",");

                String name = reloadFile[0];
                LocalDate birthday = LocalDate.parse(reloadFile[1]);
                String registeredClass = reloadFile[2];
                activity[0] = Integer.parseInt(reloadFile[3]);
                activity[1] = Integer.parseInt(reloadFile[4]);
                activity[2] = Integer.parseInt(reloadFile[5]);
                double membershipFee = Double.parseDouble(reloadFile[6]);
                double tuitionFee = Double.parseDouble(reloadFile[7]);
                String coachName = reloadFile[8];
                Coach coach1 = matchingCoach(coachName);

                member.add(new Member(name, birthday, registeredClass, activity, membershipFee, tuitionFee, coach1));

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return member;
    }

    public static ArrayList<Coach> reloadCoachFile(){
        try{
            coach.clear();

            File file = new File("Coach.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] reloadCoachFile = line.split(",");
                coach.add(new Coach(reloadCoachFile[0], reloadCoachFile[1], Integer.parseInt(reloadCoachFile[2])));

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return coach;
    }

    public static ArrayList<Treasurer> reloadTreasurerFile(){
        try{
            treasurer.clear();

            File file = new File("Treasurer.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] reloadTreasurerFile = line.split(",");
                treasurer.add(new Treasurer(reloadTreasurerFile[0], Double.parseDouble(reloadTreasurerFile[1]), Boolean.parseBoolean(reloadTreasurerFile[2]), Double.parseDouble(reloadTreasurerFile[3]), Boolean.parseBoolean(reloadTreasurerFile[4])));

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return treasurer;
    }

    public static ArrayList<TrainingRecords> reloadTrainingRecordsFile(){
        try{
            trainingRecords.clear();

            File file = new File("TrainingRecords.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] reloadTrainingRecords = line.split(",");
                trainingRecords.add(new TrainingRecords(reloadTrainingRecords[0], LocalDate.parse(reloadTrainingRecords[1]), Integer.parseInt(reloadTrainingRecords[2])));

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return trainingRecords;
    }

    public static ArrayList<CompetitionRecords> reloadCompetitionRecordsFile(){
        try{
            competitionRecords.clear();

            File file = new File("CompetitionRecords.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] reloadCompetitionRecords = line.split(",");
                competitionRecords.add(new CompetitionRecords(reloadCompetitionRecords[0], reloadCompetitionRecords[1], Integer.parseInt(reloadCompetitionRecords[2])));

            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return competitionRecords;
    }

    public static void loadTrainingInfo(){
        //get the names from the "member" arraylist and store them with default values inside the trainingRecords arraylist and then store them in the file.
        int i, j;
        for(i=0; i<member.size(); i++){
            for(j=0; j<trainingRecords.size(); j++){
                if((member.get(i).getName()).equals(trainingRecords.get(j).getName())){
                    continue;
                } else {
                    TrainingRecords trainingRecords1 = new TrainingRecords(member.get(i).getName(), LocalDate.now(), 0);
                    trainingRecords.add(trainingRecords1);
                }
            }
        }

        Files.writeTrainingRecordsToFile(trainingRecords);
    }

    public static void loadCompetitionInfo(){
        //get the names from the "member" arraylist and store them with the fees and boolean false values as default inside the treasureMem arraylist and then store them in the file.

        int i, j;
        for(i=0; i<member.size(); i++){
            for(j=0; j<competitionRecords.size(); j++){
                if((member.get(i).getName()).equals(competitionRecords.get(j).getName())){
                    continue;
                } else {
                    CompetitionRecords competitionRecords1 = new CompetitionRecords(member.get(i).getName(), "00:00", 0);
                    competitionRecords.add(competitionRecords1);
                }
            }
        }

        Files.writeCompetitionRecordsToFile(competitionRecords);
    }

    public static Coach matchingCoach(String coachName){

        Coach matchingCoach = new Coach();

        for(int i=0; i<coach.size(); i++){
            if (coachName.equals((coach.get(i)).getName()))
            {
                matchingCoach = coach.get(i);
            }
        }

        return matchingCoach;
    }


}