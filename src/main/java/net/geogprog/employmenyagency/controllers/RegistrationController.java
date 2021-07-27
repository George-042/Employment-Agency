package net.geogprog.employmenyagency.controllers;

import net.geogprog.employmenyagency.entities.User;
import net.geogprog.employmenyagency.entities.enums.ClientStatus;
import net.geogprog.employmenyagency.entities.enums.Role;
import net.geogprog.employmenyagency.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String viewSignUp() {
        return "auth/registration";
    }

    @PostMapping
    public String signUp(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "This user already exists.");
            return "auth/registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setClientStatuses(Collections.singleton(ClientStatus.UNEMPLOYED));
        userRepo.save(user);
        return "redirect:/login";
    }
}
