package com.example.l6.socialnetwork.service;

import com.example.l6.socialnetwork.Domain.*;
import com.example.l6.socialnetwork.Domain.validators.Validator;
import com.example.l6.socialnetwork.observer.Observable;
import com.example.l6.socialnetwork.observer.Observer;
import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.event.ChangeEventType;
import com.example.l6.socialnetwork.event.FriendRequestEvent;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public class FriendshipRequestService implements Observable<FriendRequestEvent>
{
    private Repository<Tuple<Long,Long>, FriendRequest> friendRequestRepository;
    private  Repository<Tuple<Long,Long>, Friendship> friendshipRepository;
    private  Repository<Long, User> userRepository;
    private Validator<FriendRequest> friendRequestValidator;

    private List<Observer<FriendRequestEvent>> observers;

    public FriendshipRequestService(Repository<Tuple<Long, Long>, FriendRequest> friendRequestRepository, Repository<Tuple<Long, Long>, Friendship> friendshipRepository, Repository<Long, User> userRepository, Validator<FriendRequest> friendRequestValidator) {
        this.friendRequestRepository = friendRequestRepository;
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.friendRequestValidator = friendRequestValidator;
        this.observers = new ArrayList<>();
    }

    public FriendshipRequestService() {
    }

    public FriendRequest addFriendRequest(FriendRequest friendRequest) throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
        this.friendRequestValidator.validate(friendRequest);

        long from = friendRequest.getFrom();
        long to = friendRequest.getTo();

        User secondFriend = this.userRepository.findOne(to);

        String errorMessage = "";
        if(secondFriend == null)
            errorMessage += "Cannot find user with id " + to + "!";

        FriendRequest f = this.friendRequestRepository.findOne(new Tuple<>(from, to));
        if(f != null && f.getStatus() != Status.REJECTED)
            errorMessage += "Cannot send friend request!";
        f = this.friendRequestRepository.findOne(new Tuple<>(to, from));
        if(f != null && f.getStatus() == Status.ACCEPTED)
            errorMessage += "You are already friends with this user!";
        if(f != null && f.getStatus() == Status.PENDING)
            errorMessage += "You have already sent a friend request to this user";

        if(errorMessage.compareTo("") != 0)
        {
            throw new ServiceException(errorMessage);
        }

        FriendRequest savedFriendRequest =  this.friendRequestRepository.save(friendRequest);
        this.notifyObservers(new FriendRequestEvent(ChangeEventType.ADD, savedFriendRequest));
        return savedFriendRequest;
    }

    public Map<User, LocalDateTime> getAll(long id)
    {
        Map<User, LocalDateTime> dto = new HashMap<>();

        Iterable<FriendRequest> iterable = this.friendRequestRepository.findAll();
        StreamSupport.stream(iterable.spliterator(), false)
                .filter(x -> (x.getId().getRight().equals(id)))
                .filter(x -> (x.getStatus() == Status.PENDING))
                .forEach( friendRequest -> {
                            User friend;
                            if(friendRequest.getId().getLeft().equals(id))
                                friend = this.userRepository.findOne(friendRequest.getId().getRight());
                            else
                                friend = this.userRepository.findOne(friendRequest.getId().getLeft());
                            dto.put(friend, friendRequest.getDate());
                        }
                );

        return dto;
    }

    public Iterable<FriendRequest> getAll()
    {
        return this.friendRequestRepository.findAll();
    }

    public void accept(long from, long to) throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
        FriendRequest friendRequest = this.friendRequestRepository.findOne(new Tuple<>(from, to));

        if(friendRequest == null)
            throw new ServiceException("There is no friend request from user with ID " + from + "!");
        if(friendRequest.getStatus() != Status.PENDING)
            throw new ServiceException("Can't accept this friend request!");

        friendRequest.setStatus(Status.ACCEPTED);
        friendRequest.setDate(LocalDateTime.now());

        this.friendRequestRepository.update(friendRequest);
        this.notifyObservers(new FriendRequestEvent(ChangeEventType.UPDATE, friendRequest));

        Friendship friendship = new Friendship();
        friendship.setId(new Tuple<>(from, to));

        this.friendshipRepository.save(friendship);
    }

    public void reject(long from, long to)
    {
        FriendRequest friendRequest = this.friendRequestRepository.findOne(new Tuple<>(from, to));

        if(friendRequest == null)
            throw new ServiceException("There is no friend request from user with ID " + from + "!");
        if(friendRequest.getStatus() != Status.PENDING)
            throw new ServiceException("Can't accept this friend request!");

        FriendRequest deletedFriendRequest = this.friendRequestRepository.delete(new Tuple<>(from, to));
        this.notifyObservers(new FriendRequestEvent(ChangeEventType.DELETE, deletedFriendRequest));
    }

    public void remove(long from, long to)
    {
        FriendRequest friendRequest = this.friendRequestRepository.findOne(new Tuple<>(from, to));

        if(friendRequest.getStatus() != Status.PENDING)
            throw new ServiceException("Cannot remove friend request!");

        this.friendRequestRepository.delete(new Tuple<>(from, to));
        FriendRequest deletedFriendRequest = this.friendRequestRepository.delete(new Tuple<>(from, to));
        this.notifyObservers(new FriendRequestEvent(ChangeEventType.DELETE, deletedFriendRequest));
    }

    public FriendRequest findRequest(long id1, long id2)
    {
        Tuple<Long,Long> id = new Tuple<>(id1, id2);
        return this.friendRequestRepository.findOne(id);
    }

    @Override
    public void addObserver(Observer<FriendRequestEvent> e)
    {
        this.observers.add(e);
    }

    @Override
    public void removeObserver(Observer<FriendRequestEvent> e)
    {
        this.observers.remove(e);
    }

    @Override
    public void notifyObservers(FriendRequestEvent t)
    {
        observers.forEach(x->x.update(t));
    }
}

