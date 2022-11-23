package com.wildcodeschool.MyAppWithDB.controller;

import com.wildcodeschool.MyAppWithDB.repository.WizardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WizardController {
    private WizardRepository wizardRepository = new WizardRepository();

    @GetMapping("/")
    public String index() {

        return "index";

    }

    @GetMapping("/wizards")
    public String getAll(Model model) {

        model.addAttribute("wizards", wizardRepository.findAll());

        return "wizard_get_all";
    }

    @GetMapping("/wizards/search")
    public String getByLastName(Model model, @RequestParam(required = false, defaultValue = "%") String lastName) {

        model.addAttribute("wizards", wizardRepository.findByLastName(lastName));

        return "wizard_get_all";
    }
}
