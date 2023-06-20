package repository;

import domain.Participant;
import domain.Referee;
import domain.Result;

import java.util.List;

public interface ResultRepository extends Repository<Integer, Result> {
    List<Participant> getEvaluatedParticipants(Referee referee);
}
