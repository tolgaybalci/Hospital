package com.github.tolgaybalci.hospital.validator;

import com.github.tolgaybalci.hospital.domain.Department;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created on March, 2018
 *
 * @author Adilcan Eren
 */
public class DepartmentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Department.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {

		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		Department d = (Department) obj;
		if(d.getName().contains("Departmanı")){
			e.rejectValue("name", "name.department");
		}
	}

}
