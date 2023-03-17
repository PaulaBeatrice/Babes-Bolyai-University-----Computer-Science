package com.example.l6.socialnetwork.service;

import com.example.l6.socialnetwork.observer.Observable;
import com.example.l6.socialnetwork.repository.Repository;
import com.example.l6.socialnetwork.Domain.Account;
import com.example.l6.socialnetwork.Domain.Friendship;
import com.example.l6.socialnetwork.Domain.Tuple;
import com.example.l6.socialnetwork.Domain.User;
import com.example.l6.socialnetwork.Domain.validators.Validator;
import com.example.l6.socialnetwork.event.ChangeEventType;
import com.example.l6.socialnetwork.event.UserEvent;
import com.example.l6.socialnetwork.observer.Observer;

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

public class UserService implements Observable<UserEvent>
{
    private final Repository<Long, User> userRepository;
    private final Repository<Tuple<Long,Long>, Friendship> friendshipRepository;
    private final Repository<String, Account> accountRepository;
    private final Validator<User> validator;
    private final Validator<Account> accountValidator;

    private final List<Observer<UserEvent>> observers;

    public UserService(Repository<Long,User> userRepository, Repository<Tuple<Long,Long>,Friendship> friendshipRepository, Repository<String, Account> accountRepository, Validator<User> validator, Validator<Account> accountValidator)
    {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.accountRepository = accountRepository;
        this.validator = validator;
        this.accountValidator = accountValidator;
        this.observers = new ArrayList<>();
    }

    public Long generateUserID()
    {
        Long userID = 1L;
        Iterable <User> all = getAll();

        for(User u : all)
            userID++;

        return userID;
    }

    public User addUser(User user, Account account) throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
        this.validator.validate(user);
        this.accountValidator.validate(account);

        if(this.accountRepository.findOne(account.getId()) != null)
        {
            throw new ServiceException("Email taken!");
        }

        User savedUser = this.userRepository.save(user);
        this.accountRepository.save(account);
        this.notifyObservers(new UserEvent(ChangeEventType.ADD, savedUser));
        return savedUser;
    }

    public User deleteUser(long id)
    {
        User user = this.userRepository.findOne(id);
        if(user == null)
            throw new ServiceException("There is no user with given ID!");

        this.accountRepository.delete(user.getEmail());
        User deletedUser = this.userRepository.delete(id);
        this.notifyObservers(new UserEvent(ChangeEventType.DELETE, deletedUser));
        return deletedUser;
    }

    public Iterable<User> getAll()
    {
        List<User> users = new ArrayList<>();

        for(User user : this.userRepository.findAll())
        {
            Map<User, LocalDateTime> friends = this.getFriends(user.getId());
            for(User friend : friends.keySet()) { user.getFriends().add(friend); }
            users.add(user);
        }
        return users;
    }

    public Map<User, LocalDateTime> getFriends(long id)
    {
        Map<User, LocalDateTime> dto = new HashMap<>();

        Iterable<Friendship> iterable = this.friendshipRepository.findAll();
        StreamSupport.stream(iterable.spliterator(), false)
                .filter(x -> (x.getId().getLeft().equals(id) || x.getId().getRight().equals(id)))
                .forEach( friendship -> {
                            User friend;
                            if(friendship.getId().getLeft().equals(id))
                                friend = this.userRepository.findOne(friendship.getId().getRight());
                            else
                                friend = this.userRepository.findOne(friendship.getId().getLeft());
                            dto.put(friend, friendship.getDate());
                        }
                );

        return dto;
    }

    public User findUser(long id)
    {
        return this.userRepository.findOne(id);
    }

    public User findUserByName(String name)
    {
        User[] user = new User[1];
        Iterable<User> iterable = this.userRepository.findAll();
        StreamSupport.stream(iterable.spliterator(), false)
                .forEach(x -> {if (x.getFirstName().compareTo(name) == 0)
                    user[0] = x;
                });
        return user[0];
    }

    public Account findAccount(String username)
    {
        Account account = accountRepository.findOne(username);
        if (account == null)
            throw new ServiceException("Account does not exist!");
        return account;
    }

    @Override
    public void addObserver(Observer<UserEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<UserEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(UserEvent t) {
        observers.forEach(x -> x.update(t));
    }
}
