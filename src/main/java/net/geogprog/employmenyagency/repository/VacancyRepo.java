package net.geogprog.employmenyagency.repository;

import net.geogprog.employmenyagency.entities.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacancyRepo extends CrudRepository<Vacancy, Long> {

    List<Vacancy> findByName(String name);
}
