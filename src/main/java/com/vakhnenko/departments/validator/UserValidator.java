package com.vakhnenko.departments.validator;

import com.vakhnenko.departments.entity.User;
import com.vakhnenko.departments.service.UserService;
import com.vakhnenko.departments.utils.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.vakhnenko.departments.entity.Constants.MAX_LENGTH_USERNAME;
import static com.vakhnenko.departments.entity.Constants.MIN_LENGTH_USERNAME;

@Component
@PropertySource("/WEB-INF/properties/messages.properties")
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < MIN_LENGTH_USERNAME || user.getUsername().length() > MAX_LENGTH_USERNAME) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        String password = user.getPassword();

        /**
         if (password.length() < MIN_LENGTH_PASSWORD || password.length() > MAX_LENGTH_PASSWORD) {
         errors.rejectValue("password", "Size.userForm.password");
         }*/

        if (!Strings.digitsAndCharactersOnly(password)) {
            errors.rejectValue("password", "Contains.userForm.password");
        }

        if (!user.getConfirmPassword().equals(password)) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
