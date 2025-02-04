package com.test.qp_assessment;

import com.test.qp_assessment.model.Role;
import com.test.qp_assessment.model.User;
import com.test.qp_assessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
            User admin=userRepo.findById(1L).orElse(null);
            if(admin==null) {
                User user=new User();
                user.setId(1L);
                user.setName("Admin");
                user.setRole(Role.ADMIN);
                user.setUsername("admin");
                user.setPassword("admin");
                userRepo.save(user);

            }
            User user=userRepo.findById(2L).orElse(null);
            if(user==null) {
                User user1 = new User();
                user1.setId(2L);
                user1.setName("User");
                user1.setRole(Role.USER);
                user1.setUsername("user");
                user1.setPassword("user");
                userRepo.save(user1);
            }
        }


}
