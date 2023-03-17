package com.example.l6.socialnetwork.Domain.validators;

public interface Validator<T>
{
    void validate(T entity) throws ValidationException;
}