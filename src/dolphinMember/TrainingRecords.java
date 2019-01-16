package dolphinMember;

import java.time.LocalDate;

public class TrainingRecords extends Records {

    public TrainingRecords(String name, LocalDate date, int trainingResult){
        super.name = name;
        super.date = date;
        super.trainingResult = trainingResult;
    }

}
