package com.pm.authservice.Service;
import com.pm.authservice.Entity.User;
import com.pm.authservice.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }


}
