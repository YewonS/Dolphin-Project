package dolphinMember;

import java.time.LocalDate;
import dolphinMain.Coach;

public class Member {

    private String name;
    private LocalDate birthday;
    private String registeredClass;
    private int[] activity = new int[4];
    private double membershipFee;
    private double tuitionFee;
    private Coach coach;

    public Member(){
    }

    public Member(String name, LocalDate birthday, String registeredClass, int[] activity){
        this.name = name;
        this.birthday = birthday;
        this.registeredClass = registeredClass;
        this.activity = activity;
    }

    public Member(String name, LocalDate birthday, String registeredClass, int[] activity, double membershipFee, double tuitionFee, Coach coach){
        this.name = name;
        this.birthday = birthday;
        this.registeredClass = registeredClass;
        this.activity = activity;
        this.membershipFee = membershipFee;
        this.tuitionFee = tuitionFee;
        this.coach = coach;
    }

    /**
     * getters and setters
     * @return return value of the getters and setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getRegisteredClass() { return registeredClass; }

    public void setRegisteredClass(String registeredClass) {
        this.registeredClass = registeredClass;
    }

    public int[] getActivity() { return activity; }

    public void setActivity(int[] activity) {
        this.activity = activity;
    }

    public int getActivity1() {
        return activity[0];
    }

    public void setActivity1(int[] activity) {
        this.activity[0] = activity[0];
    }

    public int getActivity2() {
        return activity[1];
    }

    public void setActivity2(int[] activity) {
        this.activity[1] = activity[1];
    }

    public int getActivity3() {
        return activity[2];
    }

    public void setActivity3(int[] activity) {
        this.activity[2] = activity[2];
    }

    public int getActivity4() { return activity[3]; }

    public void setActivity4(int[] activity) { this.activity[3] = activity[3]; }

    public double getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(double membershipFee) {
        this.membershipFee = membershipFee;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(double tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

}
