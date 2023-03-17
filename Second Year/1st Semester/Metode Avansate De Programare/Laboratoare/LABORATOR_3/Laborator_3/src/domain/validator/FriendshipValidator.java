package domain.validator;

import domain.Friendship;
import domain.User;
import repository.Repository;

public class FriendshipValidator implements Validator<Friendship>{
    private final Repository<User,Long> repo;

    public FriendshipValidator(Repository<User, Long> repo)
    {
        this.repo = repo;
    }
    @Override
    public void validate(Friendship entity) throws ValidationException {
        User u1 = repo.findOne(entity.getIdUser1());
        User u2 = repo.findOne(entity.getIdUser2());
        if(entity.equals(null))
            throw new ValidationException("The entity can not be null!");
        if(entity.getIdUser1() == null || entity.getIdUser2() == null)
            throw new ValidationException("The id can not be null!");
        if(u1 == null || u2 == null)
            throw new ValidationException("The id does not exist!");
    }
}
