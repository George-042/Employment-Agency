package net.geogprog.employmenyagency.controllers;

import net.geogprog.employmenyagency.entities.User;
import net.geogprog.employmenyagency.entities.enums.ClientStatus;
import net.geogprog.employmenyagency.entities.enums.Role;
import net.geogprog.employmenyagency.repository.FormRepo;
import net.geogprog.employmenyagency.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private final UserRepo userRepo;
    private final FormRepo formRepo;

    public UserController(UserRepo userRepo, FormRepo formRepo) {
        this.userRepo = userRepo;
        this.formRepo = formRepo;
    }

    @GetMapping
    public String viewUserList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "admin/userList";
    }

    @GetMapping("{user}")
    public String viewUserEdit(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "admin/userEdit";
    }

    @GetMapping("/status/{user}")
    public String viewClientStatus(@PathVariable User user, Model model) {
        model.addAttribute("usr", user);
        model.addAttribute("statuses", ClientStatus.values());
        return "admin/clientStatusEdit";
    }

    @PostMapping("/status")
    public String updateStatus(@RequestParam("userId") User user,
                               @RequestParam Map<String, String> form) {
        Set<String> statuses = Arrays.stream(ClientStatus.values())
                .map(ClientStatus::name)
                .collect(Collectors.toSet());

        user.getClientStatuses().clear();

        for (String key : form.keySet()) {
            if (statuses.contains(key)) {
                user.getClientStatuses().add(ClientStatus.valueOf(key));
            }
        }
        userRepo.save(user);
        return "redirect:/user";
    }

    @PostMapping
    public String updateUser(@RequestParam("userId") User user,
                             @RequestParam Map<String, String> form,
                             @RequestParam String username) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
        return "redirect:/user";
    }

    @GetMapping("/forms")
    public String viewUserForms(Model model) {
        model.addAttribute("forms", formRepo.findAll());
        return "admin/userForms";
    }
}
