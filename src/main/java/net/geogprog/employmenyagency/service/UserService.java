package net.geogprog.employmenyagency.service;

import net.geogprog.employmenyagency.entities.User;
import net.geogprog.employmenyagency.entities.enums.Role;
import net.geogprog.employmenyagency.repository.UserRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService, InitializingBean {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        List userRepoAll = (List) userRepo.findAll();
        if (userRepoAll.size() == 0) {
            User user = new User("admin", "24012003", "*********");
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.ADMIN));

            User user2 = new User("company", "24012003", "*********");
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.COMPANY));
            userRepo.save(user2);
        }
    }
}
