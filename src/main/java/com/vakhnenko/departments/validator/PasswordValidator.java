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

@Component
@PropertySource("/WEB-INF/properties/messages.properties")
public class PasswordValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        String password = user.getPassword();

        /*
        if (password.length() < Constants.MIN_LENGTH_PASSWORD || password.length() > Constants.MAX_LENGTH_PASSWORD) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        */

        if (!Strings.digitsAndCharactersOnly(password)) {
            errors.rejectValue("password", "Contains.userForm.password");
        }

        if (!user.getConfirmPassword().equals(password)) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
