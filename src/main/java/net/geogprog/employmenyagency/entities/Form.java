package net.geogprog.employmenyagency.entities;

import javax.persistence.*;

@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String education;
    private String experience;
    private Double expectedSalary;
    private String email;
    private String expectedJob;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Form(String name, Integer age, String education, String experience, Double expectedSalary, User user,
                String email, String expectedJob) {
        this.name = name;
        this.age = age;
        this.education = education;
        this.experience = experience;
        this.expectedSalary = expectedSalary;
        this.user = user;
        this.email = email;
        this.expectedJob = expectedJob;
    }

    public Form() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Double getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(Double expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpectedJob() {
        return expectedJob;
    }

    public void setExpectedJob(String expectedJob) {
        this.expectedJob = expectedJob;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
