package domain.validator;

import domain.Friendship;
import domain.User;
import repository.Repository;

public class FriendshipValidator implements Validator<Friendship>{
    private final Repository<User,Long> repo;
    private Friendship entity;

    public FriendshipValidator(Repository<User, Long> repo)
    {
        this.repo = repo;
    }
    @Override
    public void validate(Friendship entity) throws ValidationException {
        String errMsg = "";
        User u1 = repo.findOne(entity.getIdUser1());
        User u2 = repo.findOne(entity.getIdUser2());
        if(entity.getIdUser1() == null || entity.getIdUser2() == null)
            errMsg+="The id can not be null!";
        if(u1 == null || u2 == null)
            errMsg+="The id does not exist!";
        if(entity.getFriendsFrom() == null)
            errMsg+="The date can not be null";
        if(!errMsg.equals(""))
            throw new ValidationException(errMsg);
    }
}
