package dolphinMember;

import java.time.LocalDate;
import java.time.LocalTime;

public class Records {

    String name;
    int discipline;
    LocalDate date;
    LocalTime time1;
    String time;
    int trainingResult;
    int competitionResult;

    public Records(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscipline() { return discipline; }

    public void setDiscipline(int discipline) { this.discipline = discipline; }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalTime getTime1() { return time1; }

    public void setTime1(LocalTime time1) { this.time1 = time1; }

    public int getTrainingResult() {
        return trainingResult;
    }

    public void setTrainingResult(int trainingResult) {
        this.trainingResult = trainingResult;
    }

    public int getCompetitionResult() {
        return competitionResult;
    }

    public void setCompetitionResult(int competitionResult) {
        this.competitionResult = competitionResult;
    }

}
