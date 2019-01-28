package dolphinMember;

import java.time.LocalDate;

/**
 * TrainingRecords class is to instantiate the object Training Records,
 * so that it includes every necessary information.
 */

public class TrainingRecords extends Records {

    public TrainingRecords(String name, int discipline, LocalDate date, int trainingResult){
        super.name = name;
        super.discipline = discipline;
        super.date = date;
        super.trainingResult = trainingResult;
    }

}
