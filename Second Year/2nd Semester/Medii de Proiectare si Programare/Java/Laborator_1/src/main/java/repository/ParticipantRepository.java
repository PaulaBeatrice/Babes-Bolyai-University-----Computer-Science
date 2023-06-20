package repository;

import domain.Participant;

import java.util.List;

public interface ParticipantRepository extends Repository<Integer, Participant>{
    List<Participant> findByActivityOrderedDescByPoints(String activity);
}
