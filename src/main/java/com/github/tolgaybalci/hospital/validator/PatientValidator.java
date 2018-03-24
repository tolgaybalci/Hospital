package com.github.tolgaybalci.hospital.validator;

import com.github.tolgaybalci.hospital.domain.Patient;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class PatientValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return Patient.class.equals(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthdate.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "gender.empty");
        Patient p = (Patient) o;
        if(p.getBirthDate() != null && p.getBirthDate().isAfter(LocalDate.now())){
            errors.rejectValue("birthDate", "birthdate.after_today");
        }
    }


}
