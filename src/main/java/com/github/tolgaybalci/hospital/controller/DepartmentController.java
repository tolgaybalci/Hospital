package com.github.tolgaybalci.hospital.controller;

import com.github.tolgaybalci.hospital.domain.Department;
import com.github.tolgaybalci.hospital.repository.DepartmentRepository;
import com.github.tolgaybalci.hospital.repository.HospitalRepoitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Slf4j

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private HospitalRepoitory hospitalRepoitory;

    @GetMapping("")
    public String getDepartmentList(Model model){
        model.addAttribute("departments", departmentRepository.findAll());
        return "departments/departmentList";
    }

    @GetMapping("/new")
    public String getNewDepartment(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("hospitals", hospitalRepoitory.findAll());
        return "departments/newDepartment";
    }

    @PostMapping("/new")
    public String postNewDepartment(@ModelAttribute @Valid Department department, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            departmentRepository.save(department);
            return "redirect:/departments";
        }
        else{
           return "departments/newDepartment";
        }
    }

    @GetMapping("/{id}/update")
    public String getUpdateDepartment(@PathVariable Long id, Model model){
        if(departmentRepository.findById(id).isPresent()) {
            model.addAttribute("department", departmentRepository.findById(id).get());
            model.addAttribute("hospitals", hospitalRepoitory.findAll());
            return "departments/updateDepartment";
        }
        else{
            log.warn("There is no such a department {} with id", id);
            return "redirect:/departments";
        }
    }

    @PostMapping("/{id}/update")
    public String postUpdateDepartment(@ModelAttribute @Valid Department department, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            departmentRepository.save(department);
            return "redirect:/departments";
        }
        else{
            return "departments/updateDepartment";
        }
    }

    @PostMapping("/{id}/delete")
    public String postDeleteDepartment(@PathVariable Long id){
        if(departmentRepository.findById(id).isPresent()){
            departmentRepository.deleteById(id);
        }
        return "redirect:/departments";
    }
}
