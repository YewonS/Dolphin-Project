package dolphinMember;

import java.time.LocalDate;

public class TrainingRecords extends Records {

    public TrainingRecords(String name, int discipline, LocalDate date, int trainingResult){
        super.name = name;
        super.discipline = discipline;
        super.date = date;
        super.trainingResult = trainingResult;
    }

}
