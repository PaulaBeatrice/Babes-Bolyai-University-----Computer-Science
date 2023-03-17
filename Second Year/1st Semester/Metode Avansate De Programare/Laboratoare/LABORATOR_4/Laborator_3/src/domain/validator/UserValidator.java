package domain.validator;

import domain.User;


public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) throws ValidationException {
        String errMsg="";
        if(entity.getFirstName() == null)
            errMsg += "The first name can t be null!";
        if(entity.getFirstName().length() < 3)
            errMsg += "The first name is too short!";
        if(entity.getFirstName().contains(";"))
            errMsg += "The first name can t contain ';' !";

        if(entity.getLastName() == null)
            errMsg += "The last name can t be null!";
        if(entity.getLastName().length() < 3)
            errMsg += "The last name is too short!";
        if(entity.getLastName().contains(";"))
            errMsg += "The last name can t contain ';' !";
        if(!errMsg.equals(""))
            throw new ValidationException(errMsg);
    }
}
