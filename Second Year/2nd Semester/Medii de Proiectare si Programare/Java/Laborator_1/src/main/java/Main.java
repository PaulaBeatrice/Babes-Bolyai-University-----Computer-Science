import domain.Participant;
import domain.Referee;
import domain.Result;
import repository.ParticipantDBRepository;
import repository.RefereeDBRepository;
import repository.ResultDBRepository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Ref;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(new FileReader("C:\\FACULTATE\\Anul 2\\Semestrul 2\\Medii de Proiectare si Programare\\Laboratoare\\mpp-proiect-java-PaulaBeatrice\\Laborator_1\\bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        System.out.println(props);

        RefereeDBRepository refRepo=new RefereeDBRepository(props);
//        refRepo.save(new Referee(6,"ralu08","mancare","Raluca","Moldovan","swimming"));
//        Referee referee_d = new Referee(4,"anaaa10","floricele","Ana","Moldovan","swimming");
//        refRepo.delete(referee_d);
//        Referee referee_u = new Referee(4,"paulaaa","rozRoz","Paula","Serban","swimming");
//        refRepo.update(referee_u);
//        System.out.println("Toti referees din db");
        for(Referee referee:refRepo.getAll())
            System.out.println(referee);

        ParticipantDBRepository participantDBRepository = new ParticipantDBRepository(props);
        participantDBRepository.save(new Participant(8,"ABC","Jackson",0));
//        Participant delete_p = new Participant(3,"Nathan","Jackson",0);
//        participantDBRepository.delete(delete_p);
//        Participant update_p = new Participant(3,"Michael","Brown", 0);
//        participantDBRepository.update(update_p);
//        System.out.println("Toti participantii din db");
//        for(Participant participant:participantDBRepository.getAll())
//            System.out.println(participant);

//        ResultDBRepository resultRepo = new ResultDBRepository(props,refRepo,participantDBRepository);
//        resultRepo.save(new Result(1,participantDBRepository.find(1),refRepo.find(1),"swimming",100));
//        Result result_d = new Result(1,participantDBRepository.find(1),refRepo.find(1),"swimming",100);
//        resultRepo.delete(result_d);
//        Result result_u = new Result(1,participantDBRepository.find(1),refRepo.find(1),"swimming",50);
//        resultRepo.update(result_u);
//        for(Result result: resultRepo.getAll())
//            System.out.println(result);
    }
}
