package dolphinMain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import dolphinMember.*;

public class Treasurer implements Manager {

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

        TreasurerWork.loadInfo();

        switch(option){
            case 1:
                TreasurerWork.addPayments();
                MainScreen.userOption("treasurer");
                break;
            case 2:
                TreasurerWork.cancelPayment();
                MainScreen.userOption("treasurer");
                break;
            case 3:
                TreasurerWork.viewBehindPayment();
                MainScreen.userOption("treasurer");
                break;
            case 4:
                TreasurerWork.logout();
                break;
        }
    }

}
