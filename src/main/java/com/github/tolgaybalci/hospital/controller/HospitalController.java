package com.github.tolgaybalci.hospital.controller;

import com.github.tolgaybalci.hospital.domain.Hospital;
import com.github.tolgaybalci.hospital.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("")
    public String getHospitalList(Model model){
        model.addAttribute("hospitals", hospitalRepository.findAll());
        return "hospitals/hospitalList";
    }

    @GetMapping("/new")
    public String getNewHospital(Model model){
        model.addAttribute("hospital", new Hospital());
        return "hospitals/newHospital";
    }

    @PostMapping("/new")
    public String postNewHospital(@ModelAttribute  Hospital hospital, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hospitals/newHospital";
        }
        else{
            hospitalRepository.save(hospital);
            return "redirect:/hospitals";
        }
    }

    @GetMapping("/{id}/update")
    public String getUpdateHospital(@PathVariable Long id, Model model){
        if(!hospitalRepository.findById(id).isPresent()){
            log.warn("There is no such a hospital with id {}", id);
            return "redirect:/hospitals";
        }
        else{
            model.addAttribute("hospital", hospitalRepository.findById(id).get());
            return "hospitals/updateHospital";
        }
    }

    @PostMapping("/{id}/update")
    public String postUpdateHospital(@PathVariable Long id, @ModelAttribute Hospital hospital, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hospitals/updateHospital";
        }
        else{
            hospitalRepository.save(hospital);
            return "redirect:/hospitals";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable Long id){
        if(!hospitalRepository.findById(id).isPresent()){
            log.warn("There is no such a hospital with id {}", id);
        }
        else{
            hospitalRepository.deleteById(id);
        }
        return "redirect:/hospitals";
    }

}
