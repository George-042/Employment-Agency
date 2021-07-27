package net.geogprog.employmenyagency.controllers;

import net.geogprog.employmenyagency.entities.Form;
import net.geogprog.employmenyagency.entities.User;
import net.geogprog.employmenyagency.repository.FormRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("form")
@PreAuthorize("hasAuthority('USER')")
public class FormController {

    private final FormRepo formRepo;

    public FormController(FormRepo formRepo) {
        this.formRepo = formRepo;
    }

    @GetMapping("/add")
    public String viewAddForm() {
        return "client/addForm";
    }

    @PostMapping("/add")
    public String addForm(@AuthenticationPrincipal User user,
                          @RequestParam String name, @RequestParam String age, @RequestParam String education,
                          @RequestParam String experience, @RequestParam String expectedSalary,
                          @RequestParam String email, @RequestParam String expectedJob) {
        Form form = new Form(name, Integer.parseInt(age), education, experience, Double.parseDouble(expectedSalary),
                user, email, expectedJob);
        formRepo.save(form);
        return "redirect:/vacancy";
    }
}
