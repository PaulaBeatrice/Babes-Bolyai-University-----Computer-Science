package repository.file;

import domain.Friendship;
import domain.validator.Validator;

import java.time.LocalDateTime;
import java.util.List;

import static utils.Constants.DATE_TIME_FORMATTER;


public class FriendshipFileRepository extends AbstractFileRepository<Long, Friendship>{
    public FriendshipFileRepository(String fileName, Validator<Friendship> validator) {
        super(fileName, validator);
    }
    @Override
    public Friendship extractEntity(List<String> attributes) {
        Long id1=Long.parseLong(attributes.get(1));
        Long id2=Long.parseLong(attributes.get(2));
        LocalDateTime date= LocalDateTime.parse(attributes.get(3)); // DATE_TIME_FORMATTER
        Friendship friendship = new Friendship(id1,id2, date);
        friendship.setId(Long.parseLong(attributes.get(0)));
        return friendship;
    }

    @Override
    protected String createEntityAsString(Friendship entity) {
        return entity.getId()+";"+entity.getIdUser1()+";"+entity.getIdUser2()+";"+entity.getFriendsFrom();
    }
}
