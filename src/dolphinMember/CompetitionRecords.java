package dolphinMember;

import java.time.LocalTime;

public class CompetitionRecords extends Records {

    public CompetitionRecords(String name, int discipline, String time, int competitionResult){
        super.name = name;
        super.discipline = discipline;
        super.time = time;
        super.competitionResult = competitionResult;
    }

    public CompetitionRecords(String name, int discipline, LocalTime time1, int competitionResult){
        super.name = name;
        super.discipline = discipline;
        super.time1 = time1;
        super.competitionResult = competitionResult;
    }

}
