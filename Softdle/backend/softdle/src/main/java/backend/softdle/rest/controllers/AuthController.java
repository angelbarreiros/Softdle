package backend.softdle.rest.controllers;

import backend.softdle.model.entities.User;
import backend.softdle.model.exceptions.UserAlreadyExistsException;
import backend.softdle.model.services.AuthServiceImpl;
import backend.softdle.rest.dtos.JwtTokenDto;
import backend.softdle.rest.dtos.LoginRequestDto;
import backend.softdle.rest.dtos.RegisterRequestDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServiceImpl authServiceImpl;
    @PostMapping("/refresh")
    public JwtTokenDto refresh(@RequestAttribute String username){
        return authServiceImpl.refresh(username);

    }
    @PostMapping("/login")
    public JwtTokenDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response)  {
        User user= User.builder().username(loginRequestDto.getUsername()).password(loginRequestDto.getPassword()).build();
        JwtTokenDto jwt= authServiceImpl.login(user);
        return jwt;


    }
    @PostMapping("/register")
    public JwtTokenDto register(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
        User user= User.builder()
                .firstname(registerRequestDto.getFirstName())
                .lastname(registerRequestDto.getLastName())
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .streak(0)
                .role(User.RoleType.USER)
                .build();
        try{
            return  authServiceImpl.register(user);
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException();
        }
    }

}
