package com.test.qp_assessment.service;

import com.test.qp_assessment.model.Role;
import com.test.qp_assessment.model.User;
import com.test.qp_assessment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatService {
    @Autowired
    UserRepo userRepo;

    public boolean authenticateUser(Long id, Role role){
        User user1 = userRepo.findById(id).orElse(null);
        if(user1!=null && user1.getRole().equals(role)){
            return true;
        }
        return false;
    }
}
