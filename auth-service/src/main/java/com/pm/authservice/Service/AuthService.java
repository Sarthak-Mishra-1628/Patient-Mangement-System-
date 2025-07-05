package com.pm.authservice.Service;
import com.pm.authservice.DTO.LoginRequestDTO;
import com.pm.authservice.Entity.User;
import com.pm.authservice.Util.jwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private jwtUtil jwtUtil;

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO){
        Optional<User> userOptional = userService.findByEmail(loginRequestDTO.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
                return Optional.of(token);
            }
        }

        return Optional.empty();
    }


    public boolean validateToken(String token){
        try{
            jwtUtil.validateToken(token);
            return true;
        }
        catch(JwtException e){
            return false;
        }
    }


}
