package com.iwtg.ipaymonitor.monitor.validators.system;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.iwtg.ipaymonitor.facades.datatypes.system.DataCountry;

public class CountryValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return DataCountry.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.name.required", "name is required");
	}

}
