package service;

import domain.Friendship;
import domain.User;
import repository.database.FriendshipDBRepo;
import repository.database.UserDBRepo;

public class Network {
    private final FriendshipDBRepo repoFriendshipDB;
    private final UserDBRepo repoUserDB;

    public Network(FriendshipDBRepo repoFriendship, UserDBRepo repoUser) {
        this.repoFriendshipDB = repoFriendship;
        this.repoUserDB = repoUser;
    }

    public Long getNewIdUser() {
        Long id = 0L;
        for (User u : repoUserDB.findAll())
            if(u.getId() > id)
                id = u.getId();
        return id + 1;
    }

    public void addUser(User user) {
        user.setId(getNewIdUser());
        repoUserDB.save(user);
    }

    public Iterable<User> getUsers() {
        return repoUserDB.findAll();
    }

    public Iterable<Friendship> getFriendships() {
        return repoFriendshipDB.findAll();
    }

    public User deleteUser(Long id) {
        try {
            User t = repoUserDB.findOne(id);
            if (t == null)
                throw new IllegalArgumentException("The user does not exist!");
            for (Friendship fr : repoFriendshipDB.findAll())
                if (fr.getIdUser2().equals(id) || fr.getIdUser1().equals(id))
                    repoFriendshipDB.delete(fr.getId());
            User e = repoUserDB.delete(id);
            return e;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user!");
        }

        return null;
    }
    public User updateUser(User new_user){
        return repoUserDB.update(new_user);
    }

    public Long getNewIdFriendship() {
        Long id = 0L;
        for (Friendship u : repoFriendshipDB.findAll())
            if(u.getId() > id)
                id = u.getId();
        return id + 1;
    }

    public void addFriendship(Friendship friendship) {
        for (Friendship f : getFriendships())
            if (f.getIdUser1().equals(friendship.getIdUser1()) && f.getIdUser2().equals(friendship.getIdUser2()))
                throw new IllegalArgumentException("The friendship already exist!");
            else if (repoUserDB.findOne(friendship.getIdUser1()) == null || repoUserDB.findOne(friendship.getIdUser2()) == null)
                throw new IllegalArgumentException("The user does not exist");
        if (friendship.getIdUser2().equals(friendship.getIdUser1()))
            throw new IllegalArgumentException("The ids can not be the same!");
        friendship.setId(getNewIdFriendship());
        repoFriendshipDB.save(friendship);
    }

    public Friendship deleteFriendship(Long id) {
        if (repoFriendshipDB.findOne(id) == null)
            throw new IllegalArgumentException("The id is invalid!");
        return repoFriendshipDB.delete(id);
    }
    public Friendship updateFriendship(Friendship friendship){
        return repoFriendshipDB.update(friendship);
    }

}