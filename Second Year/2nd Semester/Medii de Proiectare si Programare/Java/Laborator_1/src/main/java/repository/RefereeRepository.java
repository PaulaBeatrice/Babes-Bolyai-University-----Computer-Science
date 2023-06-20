package repository;

import domain.Referee;

import java.util.List;

public interface RefereeRepository extends Repository<Integer, Referee>{
    List<Referee> findByActivity(String activity);
}
