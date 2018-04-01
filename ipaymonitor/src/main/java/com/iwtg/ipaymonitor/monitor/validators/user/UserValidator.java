package com.iwtg.ipaymonitor.monitor.validators.user;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.iwtg.ipaymonitor.facades.datatypes.user.DataUser;

public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return DataUser.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.name.required", "name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.lastName.required", "lastName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "field.user.required", "user is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.password.required", "password is required");	
	}

}
