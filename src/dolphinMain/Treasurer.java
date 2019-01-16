package dolphinMain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import dolphinMember.*;

public class Treasurer implements Manager {

    int i, j;
    Scanner sc = new Scanner(System.in);
    Treasurer treasurer = new Treasurer();
    ArrayList<Member> members = Files.reloadMemberFile();
    ArrayList<Treasurer> treasurerMem = Files.reloadTreasurerFile();

    String name;
    double membershipFee;
    boolean membPaid = false;
    double tuitionFee;
    boolean tuitPaid = false;

    public Treasurer(){
    }

    public Treasurer(String name, double membershipFee, boolean membPaid, double tuitionFee, boolean tuitPaid){
        this.name = name;
        this.membershipFee = membershipFee;
        this.membPaid = membPaid;
        this.tuitionFee = tuitionFee;
        this.tuitPaid = tuitPaid;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(double membershipFee) {
        this.membershipFee = membershipFee;
    }

    public boolean isMembPaid() {
        return membPaid;
    }

    public void setMembPaid(boolean membPaid) {
        this.membPaid = membPaid;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public boolean isTuitPaid() {
        return tuitPaid;
    }

    public void setTuitPaid(boolean tuitPaid) {
        this.tuitPaid = tuitPaid;
    }

    @Override
    public void selectedOption(int option) {

        loadInfo();

        switch(option){
            case 1:
                addPayments();
                MainScreen.userOption("treasurer");
                break;
            case 2:
                cancelPayment();
                MainScreen.userOption("treasurer");
                break;
            case 3:
                viewBehindPayment();
                MainScreen.userOption("treasurer");
                break;
            case 4:
                logout();
                break;
        }
    }

    public void loadInfo(){
        //get the names from the "member" arraylist and store them with the fees and boolean false values as default inside the treasureMem arraylist and then store them in the file.

        for(i=0; i<members.size(); i++){
            for(j=0; j<treasurerMem.size(); j++){
                if((members.get(i).getName()).equals(treasurerMem.get(j).getName())){
                    continue;
                } else {
                    Treasurer treasurer = new Treasurer(members.get(i).getName(), members.get(i).getMembershipFee(), false, members.get(i).getTuitionFee(), false);
                    treasurerMem.add(treasurer);
                }
            }
        }

        Files.writeTreasurerFile(treasurerMem);
    }

    public void addPayments(){
        //reload member file and then get the name, membership fees, and tuition fees. store then in the treasureMem arraylist with the boolean value.
        //also, create treasurer file and store treasureMem arraylist there, in the File class.
       try {

           System.out.println();
           System.out.println("Type in the name of the member to add payments: ");
           for (i = 0; i < members.size(); i++) {
               System.out.print((members.get(i).getName()) + " ");
           }

           String name = sc.nextLine();

           System.out.println("For membership fee, type in 'm', and for tuition fee, type in 't': ");
           String option = sc.nextLine();

           if (option.equals("m")) {

               for (i = 0; i < treasurerMem.size(); i++) {
                   if ((treasurerMem.get(i).getName()).equals(name)) {
                       System.out.println("The membership fee of the member is " + treasurerMem.get(i).getMembershipFee());
                       System.out.println("Type y if the member paid the membership fee, type n if he/she did not.");
                       String yn = sc.nextLine();
                       if (yn.equals("y")) {
                           treasurerMem.get(i).setMembPaid(true);
                       } else {
                           treasurerMem.get(i).setMembPaid(false);
                       }
                   }
               }
           } else {

               for (i = 0; i < treasurerMem.size(); i++) {
                   if ((treasurerMem.get(i).getName()).equals(name)) {
                       System.out.println("The tuition fee of the member is " + treasurerMem.get(i).getTuitionFee());
                       System.out.println("Type y if the member paid the tuition fee, type n if he/she did not.");
                       String yn = sc.nextLine();
                       if (yn.equals("y")) {
                           treasurerMem.get(i).setTuitPaid(true);
                       } else {
                           treasurerMem.get(i).setTuitPaid(false);
                       }
                   }
               }
           }

           Files.writeTreasurerFile(treasurerMem);
       } catch(InputMismatchException e){
           e.printStackTrace();
           addPayments();
       }
    }

    public void cancelPayment(){
        //get the arraylist and find the name, and then cancel it. Store the modified information.

        System.out.println();
        System.out.println("Type in the name of the member to cancel payment: ");
        for(i=0; i<members.size(); i++){
            System.out.print((members.get(i).getName()) + " ");
        }

        String name = sc.nextLine();

        System.out.println("Choose which payment to cancel: (type in 'm' for membership fee, type in 't' for tuition fee)");
        String option = sc.nextLine().toLowerCase();
        if(option.equals('m')){
            for(i=0; i<members.size(); i++){
                if((treasurerMem.get(i).getName()).equals(name)){
                    treasurerMem.get(i).setMembPaid(false);
                }
            }

            System.out.println("Membership payment of the member has successfully canceled.");

        } else if(option.equals('t')){
            for(i=0; i<members.size(); i++){
                if((treasurerMem.get(i).getName()).equals(name)){
                    treasurerMem.get(i).setTuitPaid(false);
                }
            }

            System.out.println("Tuition payment of the member has successfully canceled.");

        } else {
            System.out.println("Invalid input. Please try again.");
            cancelPayment();
        }

        Files.writeTreasurerFile(treasurerMem);
    }

    public void viewBehindPayment(){
        //get the boolean value from the arraylist and then display them.
        try {
            System.out.println();
            System.out.println("Choose which type of payment to view: (type in 'm' for membership fee, type in 't' for tuition fee)");

            String option = sc.nextLine().toLowerCase();
            if (option.equals("m")) {
                for (i = 0; i < treasurerMem.size(); i++) {
                    if (treasurerMem.get(i).isMembPaid() != true) {
                        System.out.println(treasurerMem.get(i).getName() + " ");
                    }
                }
            } else if (option.equals("t")) {
                for (i = 0; i < treasurerMem.size(); i++) {
                    if (treasurerMem.get(i).isTuitPaid() != true) {
                        System.out.println(treasurerMem.get(i).getName() + " ");
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
                viewBehindPayment();
            }
        } catch(InputMismatchException e){
            e.printStackTrace();
            viewBehindPayment();
        }
    }

    public void logout(){
        MainScreen.homeScreen();
    }
}
