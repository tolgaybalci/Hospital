package com.github.tolgaybalci.hospital.controller;

import com.github.tolgaybalci.hospital.domain.Appoint;
import com.github.tolgaybalci.hospital.domain.Department;
import com.github.tolgaybalci.hospital.domain.Doctor;
import com.github.tolgaybalci.hospital.repository.AppointRepository;
import com.github.tolgaybalci.hospital.repository.DoctorRepository;
import com.github.tolgaybalci.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/appoints")
public class AppointController {

    @Autowired
    private AppointRepository appointRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("")
    public String getAppointList(Model model){
        model.addAttribute("appoints", appointRepository.findAll());
        return "appoints/appointList";
    }

    @GetMapping("/new")
    public String getNewAppoint(Model model){
        model.addAttribute("appoint", new Appoint());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("patients", patientRepository.findAll());
        return "appoints/newAppoint";
    }

    @PostMapping("/new")
    public String postNewAppoint(@ModelAttribute @Valid Appoint appoint, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            appointRepository.save(appoint);
            return "redirect:/appoints";
        }
        else{
            return "appoints/newAppoint";
        }
    }
}
