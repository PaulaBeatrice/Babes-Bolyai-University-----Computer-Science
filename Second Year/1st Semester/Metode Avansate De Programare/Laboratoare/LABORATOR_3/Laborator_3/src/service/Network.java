package service;

import domain.Friendship;
import domain.User;
import repository.file.FriendshipFileRepository;
import repository.file.UserFileRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Network {
    private FriendshipFileRepository repoFriendship;
    private UserFileRepo repoUser;

    public Network(FriendshipFileRepository repoFriendship, UserFileRepo repoUser) {
        this.repoFriendship = repoFriendship;
        this.repoUser = repoUser;
    }

    public Long getNewIdUser()
    {
        Long id = 0L;
        for(User u:repoUser.findAll())
            id = u.getId();
        return id+1;
    }

    public void addUser(User user)
    {
        user.setId(getNewIdUser());
        repoUser.save(user);
    }

    public Iterable<User> getUsers()
    {
        return repoUser.findAll();
    }

    public Iterable<Friendship> getFriendships()
    {
        return repoFriendship.findAll();
    }

    public User deleteUser(Long id)
    {
        try{
            User t = repoUser.findOne(id);
            if(t == null)
                throw new IllegalArgumentException("The user does not exist!");
            for(Friendship fr: repoFriendship.findAll())
                if(fr.getIdUser2().equals(id) || fr.getIdUser1().equals(id))
                    repoFriendship.delete(fr.getId());
            User e = repoUser.delete(id);
            return e;
        }
        catch (IllegalArgumentException e){System.out.println("Invalid user!");}

        return null;
    }


    public Long getNewIdFriendship()
    {
        Long id = 0L;
        for(Friendship u:repoFriendship.findAll())
            id = u.getId();
        return id+1;
    }

    public void addFriendship(Friendship friendship)
    {
        for(Friendship f:getFriendships())
            if(f.getIdUser1().equals(friendship.getIdUser1()) && f.getIdUser2().equals(friendship.getIdUser2()))
                throw new IllegalArgumentException("The friendship already exist!");
        else if(repoUser.findOne(friendship.getIdUser1()) == null || repoUser.findOne(friendship.getIdUser2()) == null)
            throw new IllegalArgumentException("The user does not exist");
        if(friendship.getIdUser2().equals(friendship.getIdUser1()))
            throw new IllegalArgumentException("The ids can not be the same!");
        friendship.setId(getNewIdFriendship());
        repoFriendship.save(friendship);
    }

    public Friendship deleteFriendship(Long id)
    {
        if(repoFriendship.findOne(id) == null)
            throw new IllegalArgumentException("The id is invalid!");
        return repoFriendship.delete(id);
    }

}