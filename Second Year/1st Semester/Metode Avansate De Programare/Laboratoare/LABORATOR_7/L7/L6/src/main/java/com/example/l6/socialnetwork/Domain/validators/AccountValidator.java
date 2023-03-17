package com.example.l6.socialnetwork.Domain.validators;

import com.example.l6.socialnetwork.Domain.Account;

public class AccountValidator implements Validator<Account>
{
    @Override
    public void validate(Account entity) throws ValidationException
    {
        if(!entity.getUsername().matches("\\w+@\\w+\\.com") && !entity.getUsername().matches("\\w+@\\w+\\.ro"))
        {
            throw new ValidationException("Invalid email!");
        }
    }
}
