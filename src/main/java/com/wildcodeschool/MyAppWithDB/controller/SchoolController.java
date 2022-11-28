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

    /* GET */
    @GetMapping("/schools")
    public String getAllSchools(Model model) {
        model.addAttribute("schools", schoolRepository.findAllSchools());
        return "school_get_all";
    }

    @RequestMapping(value = "/schools/school", params = "id")
    public String getSchoolById(Model model, @RequestParam int id) {
        model.addAttribute("schools", schoolRepository.findSchoolById(id));
        return "school_get_all";
    }

    @RequestMapping(value = "/schools/school", params = "country")
    public String getSchoolsByCountry(Model model, @RequestParam(required = false, defaultValue = "%") String country) {
        model.addAttribute("schools", schoolRepository.findSchoolsByCountry(country));
        return "school_get_all";
    }

    /* POST */
    @GetMapping("/school/create")
    public String createSchool() {
        return "school_post";
    }

    @PostMapping("/school/create")
    public String postSchool(Model model,
                             @RequestParam String name,
                             @RequestParam int capacity,
                             @RequestParam String country
    ) {
        model.addAttribute("school", schoolRepository.saveNewSchool(name, capacity,
                country));

        return "school_get";
    }

    /*PUT*/
    @GetMapping("/school/update")
    public String updateSchool(Model model, @RequestParam int id) {
        model.addAttribute("school", schoolRepository.findSchoolById(id));
        return "school_put";
    }

    @PostMapping("/school/update")
    public String putSchool(Model model,
                            @RequestParam int id,
                            @RequestParam String name,
                            @RequestParam int capacity,
                            @RequestParam String country
    ) {
        model.addAttribute("school", schoolRepository.updateSchool(id, name, capacity, country));

        return "school_get";
    }

    /*DELETE*/
    @GetMapping ("/school/delete")
    public String deleteSchool(@RequestParam int id) {
        schoolRepository.deleteSchoolById(id);
        return "school_delete";
    }
}
