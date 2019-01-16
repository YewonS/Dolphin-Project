package dolphinMember;

import java.time.LocalDate;

public class Records {

    String name;
    LocalDate date;
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
