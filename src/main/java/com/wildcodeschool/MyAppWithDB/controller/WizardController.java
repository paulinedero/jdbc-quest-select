package com.wildcodeschool.MyAppWithDB.controller;

import com.wildcodeschool.MyAppWithDB.repository.WizardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WizardController {
    private WizardRepository wizardRepository = new WizardRepository();

    /* GET */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/wizards")
    public String getAllWizards(Model model) {
        model.addAttribute("wizards", wizardRepository.findAllWizards());
        return "wizard_get_all";
    }

    @GetMapping("/wizards/search")
    public String getWizardsByLastName(Model model, @RequestParam(required = false, defaultValue = "%") String lastName) {
        model.addAttribute("wizards", wizardRepository.findWizardsByLastName(lastName));
        return "wizard_get_all";
    }

    /* POST */
    @GetMapping("/wizard/create")
    public String createWizard() {
        return "wizard_post";
    }

    @PostMapping("/wizard/create")
    public String postWizard(Model model,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String birthday,
                             @RequestParam String birthPlace,
                             @RequestParam(required = false, defaultValue = "") String biography,
                             @RequestParam(required = false, defaultValue = "false") boolean isMuggle
    ) {
        model.addAttribute("wizard", wizardRepository.saveNewWizard(firstName, lastName,
                birthday, birthPlace, biography, isMuggle));

        return "wizard_get";
    }

    /*PUT*/
    @GetMapping("/wizard/update")
    public String updateWizard(Model model, @RequestParam int id) {
        model.addAttribute("wizard", wizardRepository.findWizardById(id));
        return "wizard_put";
    }

    @PostMapping("/wizard/update")
    public String putWizard(Model model,
                            @RequestParam int id,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String birthday,
                            @RequestParam String birthPlace,
                            @RequestParam(required = false, defaultValue = "") String biography,
                            @RequestParam(required = false, defaultValue = "false") boolean isMuggle
    ) {
        model.addAttribute("wizard", wizardRepository.updateWizard(id, firstName, lastName,
                birthday, birthPlace, biography, isMuggle));

        return "wizard_get";
    }

    /*DELETE*/
    @GetMapping ("/wizard/delete")
    public String deleteWizard(@RequestParam int id) {
        wizardRepository.deleteWizardById(id);
        return "wizard_delete";
    }
}
