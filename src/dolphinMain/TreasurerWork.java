package dolphinMain;

import dolphinMember.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class TreasurerWork {

    static int i, j;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Member> members = Files.reloadMemberFile();
    static ArrayList<Treasurer> treasurerMem = Files.reloadTreasurerFile();

    public static void loadInfo(){
        //get the names from the "member" arraylist and store them with the fees and boolean false values as default inside the treasureMem arraylist and then store them in the file.

        for(i=0; i<members.size(); i++){
            boolean found = false;
            for(Treasurer treasurer : treasurerMem){
                if(treasurer.getName().equals(members.get(i).getName())){
                    found = true;
                }
            }
            if(found){
                continue;
            } else {
                Treasurer treasurer = new Treasurer(members.get(i).getName(), members.get(i).getMembershipFee(), false, members.get(i).getTuitionFee(), false);
                treasurerMem.add(treasurer);
            }
        }

        Files.writeTreasurerFile(treasurerMem);

    }

    public static void modifyInfo(Member memberToModify){
        for(Treasurer treasurer : treasurerMem){
            if(treasurer.getName().equals(memberToModify.getName())){
                treasurer.setMembershipFee(memberToModify.getMembershipFee());
                treasurer.setTuitionFee(memberToModify.getTuitionFee());
            }
        }

        System.out.println("Modified information saved to treasurer file.");
        Files.writeTreasurerFile(treasurerMem);
    }

    public static void addPayments(){
        //reload member file and then get the name, membership fees, and tuition fees. store then in the treasureMem arraylist with the boolean value.
        //also, create treasurer file and store treasureMem arraylist there, in the File class.
        try {

            System.out.println();
            System.out.println("Type in the name of the member to add payments: ");
            for (i = 0; i < members.size(); i++) {
                System.out.print((members.get(i).getName()) + " ");
            }

            String name = sc.nextLine();
            sc.nextLine();

            System.out.println("For membership fee, type in 1, and for tuition fee, type in 2: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {

                for (Treasurer treasurer : treasurerMem) {
                    if (treasurer.getName().equals(name)) {
                        System.out.println("The membership fee of the member is " + treasurer.getMembershipFee());
                        System.out.println("Type y if the member paid the membership fee, type n if he/she did not.");
                        String yn = sc.nextLine();
                        if (yn.equals("y")) {
                            treasurer.setMembPaid(true);
                            System.out.println("Payment saved.");
                        } else {
                            treasurer.setMembPaid(false);
                            System.out.println("Payment not saved.");
                        }
                    }
                }
            } else if (option == 2){

                for (Treasurer treasurer : treasurerMem) {
                    if(treasurer.getName().equals(name)){
                        System.out.println("The tuition fee of the member is " + treasurer.getTuitionFee());
                        System.out.println("Type y if the member paid the tuition fee, type n if he/she did not.");
                        String yn = sc.nextLine();
                        if (yn.equals("y")) {
                            treasurer.setTuitPaid(true);
                            System.out.println("Payment saved.");
                        } else {
                            treasurer.setTuitPaid(false);
                            System.out.println("Payment not saved.");
                        }
                    }
                }
            } else {
                System.out.println("Invalid input. Try again.");
                addPayments();
            }

            Files.writeTreasurerFile(treasurerMem);

        } catch(InputMismatchException e){
            e.printStackTrace();
            addPayments();
        }
    }

    public static void cancelPayment(){
        //get the arraylist and find the name, and then cancel it. Store the modified information.
        try {
            System.out.println();
            System.out.println("Type in the name of the member to cancel payment: ");
            for (Member member : members) {
                System.out.print(member.getName() + " ");
            }

            String name = sc.nextLine();
            sc.nextLine();

            System.out.println("Choose which payment to cancel: (type in '1' for membership fee, type in '2' for tuition fee)");
            int option = sc.nextInt();
            if (option == 1) {
                for (Treasurer treasurer : treasurerMem) {
                    if (treasurer.getName().equals(name)) {
                        treasurer.setMembPaid(false);
                    }
                }

                System.out.println("Membership payment of the member has successfully canceled.");

            } else if (option == 2) {
                for (Treasurer treasurer1 : treasurerMem){
                    if (treasurer1.getName().equals(name)) {
                        treasurer1.setTuitPaid(false);
                }

                System.out.println("Tuition payment of the member has successfully canceled.");

                }
            } else {
                System.out.println("Invalid input. Please try again.");
                cancelPayment();
            }
        } catch (InputMismatchException e){
            e.printStackTrace();
            cancelPayment();
        }
        Files.writeTreasurerFile(treasurerMem);
    }

    public static void viewBehindPayment(){
        //get the boolean value from the arraylist and then display them.
        try {
            System.out.println();
            System.out.println("Choose which type of payment to view: (type in '1' for membership fee, type in '2' for tuition fee)");

            int option = sc.nextInt();
            if (option == 1) {
                for (Treasurer treasurer2 : treasurerMem) {
                    if (!treasurer2.isMembPaid()) {
                        System.out.println(treasurer2.getName() + " ");
                    }
                }
            } else if (option == 2) {
                for (Treasurer treasurer2 : treasurerMem) {
                    if (!treasurer2.isTuitPaid()) {
                        System.out.println(treasurer2.getName() + " ");
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

    public static void logout(){
        MainScreen.homeScreen();
    }
}
