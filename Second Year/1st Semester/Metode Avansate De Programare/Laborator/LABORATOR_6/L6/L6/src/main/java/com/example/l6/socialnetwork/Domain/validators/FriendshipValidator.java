package com.example.l6.socialnetwork.Domain.validators;

import com.example.l6.socialnetwork.Domain.Friendship;

public class FriendshipValidator implements Validator<Friendship>
{
    @Override
    public void validate(Friendship entity) throws ValidationException
    {
        long firstFriend = entity.getId().getLeft();
        long secondFriend = entity.getId().getRight();
        if(secondFriend == firstFriend)
            throw new ValidationException("ID's cannot be the same!");
    }
}
