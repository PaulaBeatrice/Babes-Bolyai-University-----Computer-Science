package com.example.l6.socialnetwork.service;

import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.Domain.FriendRequest;
import com.example.l6.socialnetwork.Domain.Friendship;
import com.example.l6.socialnetwork.Domain.Tuple;
import com.example.l6.socialnetwork.Domain.User;
import com.example.l6.socialnetwork.Domain.validators.Validator;
import com.example.l6.socialnetwork.event.ChangeEventType;
import com.example.l6.socialnetwork.event.FriendshipEvent;
import com.example.l6.socialnetwork.observer.Observable;
import com.example.l6.socialnetwork.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendshipService implements Observable<FriendshipEvent>
{
    private final Repository<Long, User> userRepository;
    private final Repository<Tuple<Long, Long>, FriendRequest> friendRequestRepository;
    private final Repository<Tuple<Long, Long>, Friendship> friendshipRepository;
    private final Validator<Friendship> friendshipValidator;

    private List<Observer<FriendshipEvent>> observers;

    public FriendshipService(Repository<Long, User> userRepository, Repository<Tuple<Long, Long>, FriendRequest> friendRequestRepository, Repository<Tuple<Long, Long>, Friendship> friendshipRepository, Validator<Friendship> friendshipValidator)
    {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.friendshipRepository = friendshipRepository;
        this.friendshipValidator = friendshipValidator;
        this.observers = new ArrayList<>();
    }

    public Friendship removeFriendship(Friendship friendship) throws ServiceException
    {
        this.friendshipValidator.validate(friendship);

        long firstID = friendship.getId().getLeft();
        long secondID = friendship.getId().getRight();

        User firstFriend = this.userRepository.findOne(firstID);
        User secondFriend = this.userRepository.findOne(secondID);

        String errorMessage = "";
        if(firstFriend == null)
            errorMessage += "Cannot find user with id " + firstID + "!\n";
        if(secondFriend == null)
            errorMessage += "Cannot find user with id " + secondID + "!\n";

        if(errorMessage.compareTo("") != 0)
            throw new ServiceException(errorMessage);

        Friendship f = this.friendshipRepository.findOne(new Tuple<>(firstID, secondID));
        if(f != null)
        {
            this.friendshipRepository.delete(f.getId());
            this.notifyObservers(new FriendshipEvent(ChangeEventType.DELETE, f));
            return f;
        }
        return null;
    }

    public Friendship findFriendship(long id1, long id2)
    {
        Tuple<Long,Long> id = new Tuple<>(id1, id2);
        return this.friendshipRepository.findOne(id);
    }

    @Override
    public void addObserver(Observer<FriendshipEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<FriendshipEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(FriendshipEvent t) {
        observers.forEach(x->x.update(t));
    }
}
