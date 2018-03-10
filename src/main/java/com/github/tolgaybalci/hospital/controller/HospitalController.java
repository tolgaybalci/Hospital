package com.github.tolgaybalci.hospital.controller;

import com.github.tolgaybalci.hospital.domain.Hospital;
import com.github.tolgaybalci.hospital.repository.HospitalRepoitory;
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
    private HospitalRepoitory hospitalRepoitory;

    @GetMapping("")
    public String getHospitalList(Model model){
        model.addAttribute("hospitals", hospitalRepoitory.findAll());
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
            hospitalRepoitory.save(hospital);
            return "redirect:/hospitals";
        }
    }

    @GetMapping("/{id}/update")
    public String getUpdateHospital(@PathVariable Long id, Model model){
        if(!hospitalRepoitory.findById(id).isPresent()){
            log.warn("There is no such a hospital with id {}", id);
            return "redirect:/hospitals";
        }
        else{
            model.addAttribute("hospital", hospitalRepoitory.findById(id).get());
            return "hospitals/updateHospital";
        }
    }

    @PostMapping("/{id}/update")
    public String postUpdateHospital(@PathVariable Long id, @ModelAttribute Hospital hospital, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "hospitals/updateHospital";
        }
        else{
            hospitalRepoitory.save(hospital);
            return "redirect:/hospitals";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteHospital(@PathVariable Long id){
        if(!hospitalRepoitory.findById(id).isPresent()){
            log.warn("There is no such a hospital with id {}", id);
        }
        else{
            hospitalRepoitory.deleteById(id);
        }
        return "redirect:/hospitals";
    }

}
