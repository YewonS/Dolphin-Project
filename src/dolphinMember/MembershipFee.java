package dolphinMember;

import java.time.LocalDate;
import java.time.Period;

/**
 * MembershipFee class is for calculating fees
 * for memberships and tuition.
 */

public class MembershipFee {

    static double annualMembershipFee;
    static double juniorFee = 1000;
    static double seniorFee = 1600;
    static double passiveFee = 500;
    static double classTuitionFee;

    /**
     * Calculates membership fees
     * @param member the member who needs to get the membership fees calculated
     * @return the membership fee that the member needs to pay
     */
    public static double calculateMembershipFee(Member member){

        int memberActivity[] = new int[3];
        memberActivity[0] = member.getActivity1();
        memberActivity[1] = member.getActivity2();
        memberActivity[2] = member.getActivity3();

        LocalDate bday = member.getBirthday();
        LocalDate today = LocalDate.now();
        Period period = Period.between(bday, today);

        //active membership
        if(memberActivity[0] == 1) {
            if (period.getYears() < 18) {
                annualMembershipFee = juniorFee;
            } else if (period.getYears() < 60) {
                annualMembershipFee = seniorFee;
            } else if (period.getYears() >= 60) {
                annualMembershipFee= seniorFee - (seniorFee * 0.25);
            }
        //passive membership
        } else {
            annualMembershipFee = passiveFee;
        }

        return annualMembershipFee;
    }

    /**
     * Calculates tuition fees
     * @param member the member who needs to get the tuition fees calculated
     * @return the tuition fee that the member needs to pay
     */
    public static double calculateTuitionFee(Member member){

        //calculate tuition fee for the registered classes
        String registeredClass = member.getRegisteredClass().toLowerCase();
        switch(registeredClass){
            case "none":
                classTuitionFee = 0;
                break;
            case "beginner":
                classTuitionFee = 200;
                break;
            case "intermediate":
                classTuitionFee = 250;
                break;
            case "advanced":
                classTuitionFee = 300;
                break;
            default:
                classTuitionFee = 0;
                break;
        }

        return classTuitionFee;
    }
}
