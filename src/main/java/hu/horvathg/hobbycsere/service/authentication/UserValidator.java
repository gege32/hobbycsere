package hu.horvathg.hobbycsere.service.authentication;

import java.util.Arrays;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import hu.horvathg.hobbycsere.model.user.User;
import hu.horvathg.hobbycsere.service.user.UserService;

@Component
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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("email", "Size.userForm.username");
        }
        if (userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.username");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30), 
                new UppercaseCharacterRule(1), 
                new DigitCharacterRule(1), 
                new SpecialCharacterRule(1), 
                new WhitespaceRule()));
        RuleResult result = validator.validate(new PasswordData(user.getPassword()));
        if (!result.isValid()) {
        	errors.rejectValue("password", "Pass.userForm.criteriaerror");
        }
    }
}
