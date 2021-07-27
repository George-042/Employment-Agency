package net.geogprog.employmenyagency.controllers;

import net.geogprog.employmenyagency.entities.User;
import net.geogprog.employmenyagency.entities.Vacancy;
import net.geogprog.employmenyagency.entities.enums.VacancyStatus;
import net.geogprog.employmenyagency.repository.UserRepo;
import net.geogprog.employmenyagency.repository.VacancyRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/vacancy")
public class VacancyController {

    private final VacancyRepo vacancyRepo;
    private final UserRepo userRepo;

//    @Value("${upload.path}")
//    private String uploadPath;

    public VacancyController(VacancyRepo vacancyRepo, UserRepo userRepo) {
        this.vacancyRepo = vacancyRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String viewVacancy(Model model) {
        Iterable<Vacancy> vacancies = vacancyRepo.findAll();
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/add")
    public String viewAddVacancy() {
        return "company/addVacancy";
    }

    @PostMapping("/add")
    public String addVacancy(@AuthenticationPrincipal User user,
                             @RequestParam String name, @RequestParam String salary, @RequestParam String schedule,
                             @RequestParam String expectedEducation, @RequestParam String expectedExperience,
                             @RequestParam String companyName) {
//                             @RequestParam("file") MultipartFile file)
//            throws IOException {
        Vacancy vacancy = new Vacancy(name, Double.parseDouble(salary), schedule, expectedEducation, expectedExperience,
                companyName, user, Collections.singleton(VacancyStatus.AVAILABLE));
//        if (file != null && !file.getOriginalFilename().isEmpty()) {
//            File uploadDir = new File(uploadPath);
//
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//            file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//            vacancy.setFilename(resultFilename);
//        }
        vacancyRepo.save(vacancy);
        return "redirect:/vacancy";
    }

    @GetMapping("/{id}")
    public String viewDetails(@PathVariable(value = "id") long id, Model model) {
        if (!vacancyRepo.existsById(id)) {
            return "error/error";
        }
        Optional<Vacancy> vacancy = vacancyRepo.findById(id);
        ArrayList<Vacancy> res = new ArrayList<>();
        vacancy.ifPresent(res::add);
        model.addAttribute("vacancy", res);
        return "vacancyDetails";
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @GetMapping("/{id}/edit")
    public String viewVacancyEdit(@PathVariable(value = "id") long id, Model model) {
        if (!vacancyRepo.existsById(id)) {
            return "error/error";
        }
        Optional<Vacancy> vacancy = vacancyRepo.findById(id);
        ArrayList<Vacancy> res = new ArrayList<>();
        vacancy.ifPresent(res::add);
        model.addAttribute("vacancy", res);
        return "company/editVacancy";
    }

    @PostMapping("/{id}/edit")
    public String updateVacancy(@PathVariable(value = "id") long id,
                                @RequestParam String name, @RequestParam String salary, @RequestParam String schedule,
                                @RequestParam String expectedEducation, @RequestParam String expectedExperience,
                                @RequestParam String companyName) {
        Vacancy vacancy = vacancyRepo.findById(id).orElseThrow();
        vacancy.setName(name);
        vacancy.setSalary(Double.parseDouble(salary));
        vacancy.setSchedule(schedule);
        vacancy.setExpectedEducation(expectedEducation);
        vacancy.setExpectedExperience(expectedExperience);
        vacancy.setCompanyName(companyName);
        vacancyRepo.save(vacancy);
        return "redirect:/vacancy";
    }

    @PreAuthorize("hasAuthority('COMPANY')")
    @PostMapping("/{id}/remove")
    public String deleteVacancy(@PathVariable(value = "id") long id) {
        Vacancy vacancy = vacancyRepo.findById(id).orElseThrow();
        vacancyRepo.delete(vacancy);
        return "redirect:/vacancy";
    }

    @PostMapping
    public String search(@RequestParam String search, Model model) {
        Iterable<Vacancy> vacancies;
        if (search != null && !search.isEmpty()) {
            vacancies = vacancyRepo.findByName(search);
        } else {
            vacancies = vacancyRepo.findAll();
        }
        model.addAttribute("vacancies", vacancies);
        return "vacancies";
    }
}
