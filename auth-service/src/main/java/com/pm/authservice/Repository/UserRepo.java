package com.pm.authservice.Repository;
import com.pm.authservice.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface UserRepo extends JpaRepository<User,UUID>{

    public Optional<User> findByEmail(String email);

}