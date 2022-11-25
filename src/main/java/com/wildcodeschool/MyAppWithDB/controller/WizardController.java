package com.wildcodeschool.MyAppWithDB.controller;

import com.wildcodeschool.MyAppWithDB.repository.WizardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


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

    @GetMapping("/wizards/add")
    public String addWizard() {
        return "wizard_post";
    }
    @PostMapping("/wizard/create")
    public String postWizard(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String birthday,
                             @RequestParam String birthPlace,
                             @RequestParam(required = false, defaultValue = "") String biography,
                             @RequestParam(required = false, defaultValue = "false") boolean muggle
    ) {
        model.addAttribute("wizard", wizardRepository.save(firstName, lastName,
                birthday, birthPlace, biography, muggle));

        return "wizard_get";
    }


}
