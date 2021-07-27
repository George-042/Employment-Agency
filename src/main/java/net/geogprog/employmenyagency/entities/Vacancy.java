package net.geogprog.employmenyagency.entities;

import net.geogprog.employmenyagency.entities.enums.VacancyStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double salary;
    private String schedule;
    private String expectedEducation;
    private String expectedExperience;
    private String companyName;
//    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection(targetClass = VacancyStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "vacancy_status", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<VacancyStatus> vacancyStatuses;

    public Vacancy(String name, Double salary, String schedule, String expectedEducation, String expectedExperience,
                   String companyName, User user, Set<VacancyStatus> vacancyStatuses) {
        this.name = name;
        this.salary = salary;
        this.schedule = schedule;
        this.expectedEducation = expectedEducation;
        this.expectedExperience = expectedExperience;
        this.companyName = companyName;
        this.user = user;
        this.vacancyStatuses = vacancyStatuses;
    }

    public Vacancy() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getExpectedEducation() {
        return expectedEducation;
    }

    public void setExpectedEducation(String expectedEducation) {
        this.expectedEducation = expectedEducation;
    }

    public String getExpectedExperience() {
        return expectedExperience;
    }

    public void setExpectedExperience(String expectedExperience) {
        this.expectedExperience = expectedExperience;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

//    public String getFilename() {
//        return filename;
//    }
//
//    public void setFilename(String filename) {
//        this.filename = filename;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<VacancyStatus> getVacancyStatuses() {
        return vacancyStatuses;
    }

    public void setVacancyStatuses(Set<VacancyStatus> vacancyStatuses) {
        this.vacancyStatuses = vacancyStatuses;
    }
}
