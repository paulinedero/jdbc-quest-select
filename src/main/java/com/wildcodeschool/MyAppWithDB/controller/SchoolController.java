package com.wildcodeschool.MyAppWithDB.controller;

import com.wildcodeschool.MyAppWithDB.repository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolController {
    private SchoolRepository schoolRepository = new SchoolRepository();

    @GetMapping("/schools")
    public String getAll(Model model) {

        model.addAttribute("schools", schoolRepository.findAll());

        return "school_get_all";
    }

    @RequestMapping(value = "/schools/school", params = "id")
    public String getSchoolById(Model model, @RequestParam String id) {

        model.addAttribute("schools", schoolRepository.findSchoolById(id));

        return "school_get_all";
    }

    @RequestMapping(value = "/schools/school", params = "country")
    public String getSchoolsByCountry(Model model, @RequestParam(required = false, defaultValue = "%") String country) {

        model.addAttribute("schools", schoolRepository.findSchoolsByCountry(country));

        return "school_get_all";
    }

    @GetMapping("/schools/add")
    public String addWizard() {
        return "school_post";
    }

    @PostMapping("/school/create")
    public String postSchool(Model model,
                             @RequestParam String schoolName,
                             @RequestParam int capacity,
                             @RequestParam String schoolCountry
    ) {
        model.addAttribute("school", schoolRepository.save(schoolName, capacity,
                schoolCountry));

        return "school_get";
    }
}
