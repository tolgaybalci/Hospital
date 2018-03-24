package com.github.tolgaybalci.hospital.controller;

import com.github.tolgaybalci.hospital.domain.Patient;
import com.github.tolgaybalci.hospital.repository.PatientRepository;
import com.github.tolgaybalci.hospital.validator.PatientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(new PatientValidator());
    }

    @GetMapping("")
    public String getPatientList(Model model){
        model.addAttribute("patients", patientRepository.findAll());
        return "patients/patientList";
    }

    @GetMapping("/new")
    public String getNewPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patients/newPatient";
    }

    @PostMapping("/new")
    public String postNewPatient(@ModelAttribute @Valid Patient patient, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            patientRepository.save(patient);
            return "redirect:/patients";
        }
        else
        {
            return "patients/newPatient";
        }
    }

    @GetMapping("/{id}/update")
    public String getUpdatePatient(@PathVariable Long id, Model model){
        if(patientRepository.findById(id).isPresent()){
            model.addAttribute("patient", patientRepository.findById(id).get());
            return "patients/updatePatient";
        }
        else
        {
            log.warn("There is no such a patient {} with id", id);
            return "redirect:/patients";
        }
    }

    @PostMapping("/{id}/update")
    public String postUpdatePatient(@ModelAttribute @Valid Patient patient, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            patientRepository.save(patient);
            return "redirect:/patients";
        }
        else
        {
            return "patients/newPatient";
        }
    }

    @PostMapping("/{id}/delete")
    public String postDeletePatient(@PathVariable Long id){
        if(patientRepository.findById(id).isPresent()){
            patientRepository.deleteById(id);
        }
        return "redirect:/patients";
    }
}
