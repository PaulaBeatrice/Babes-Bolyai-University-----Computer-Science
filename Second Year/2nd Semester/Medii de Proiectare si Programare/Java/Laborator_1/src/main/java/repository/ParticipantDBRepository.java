package repository;

import domain.Participant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantDBRepository implements ParticipantRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public ParticipantDBRepository(Properties props){
        logger.info("Initializing ParticipantDBRepository with propertiesL {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Participant findOne(Integer integer) throws IllegalArgumentException {
        logger.traceEntry();
        Connection conn = dbUtils.getConnection();
        Participant participant = null;
        try(PreparedStatement preSmt= conn.prepareStatement("select * from Participants where id=?")){
            preSmt.setInt(1,integer);
            try(ResultSet resultSet = preSmt.executeQuery()){
                if(!resultSet.next())
                    return null;

                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int points = resultSet.getInt("points");

                participant = new Participant(id,first_name,last_name,points);
            }
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(participant);
        return participant;
    }

    @Override
    public Iterable<Participant> getAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Participant> participantList = new ArrayList<>();
        try(PreparedStatement preSmt=con.prepareStatement("select * from Participants")){
            try(ResultSet resultSet = preSmt.executeQuery()){
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    int points = resultSet.getInt("points");
                    Participant participant = new Participant(id, first_name,last_name,points);
                    participantList.add(participant);
                }
            }
        } catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(participantList);
        return participantList;
    }

    @Override
    public void clear() {
        logger.traceEntry();
        Connection conn = dbUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from Participants")){
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit();
    }

    @Override
    public void save(Participant entity) {
        logger.traceEntry("saving task{} ", entity);
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Participants (first_name,last_name,points) values (?,?,?)")){
            preStmt.setString(1,entity.getFirst_name());
            preStmt.setString(2,entity.getLast_name());
            preStmt.setInt(3,entity.getPoints());
            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch(SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Participant entity) {
        logger.traceEntry();
        Connection conn = dbUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("update Participants set first_name = ?, last_name = ?, points = ? where id=?")){
            preSmt.setString(1,entity.getFirst_name());
            preSmt.setString(2,entity.getLast_name());
            preSmt.setInt(3,entity.getPoints());
            preSmt.setInt(4,entity.getId());
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(entity);
    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry();
        Connection conn = dbUtils.getConnection();
        try(PreparedStatement preSmt= conn.prepareStatement("delete from Participants where id=?")){
            preSmt.setInt(1,id);
            preSmt.executeUpdate();
        }catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(id);
    }

    @Override
    public List<Participant> findByActivityOrderedDescByPoints(String activity) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Participant> participantList = new ArrayList<>();
        try(PreparedStatement preSmt=con.prepareStatement("select * from Participants where activity = ? order by points desc")){
            preSmt.setString(1,activity);
            try(ResultSet resultSet = preSmt.executeQuery()){
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    int points = resultSet.getInt("points");
                    Participant participant = new Participant(id, first_name,last_name,points);
                    participantList.add(participant);
                }
            }
        } catch(SQLException e){
            logger.error(e);
            System.err.println("Error DB "+e);
        }
        logger.traceExit(participantList);
        return participantList;
    }
}
