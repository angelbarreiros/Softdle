package backend.softdle.rest.controllers;

import backend.softdle.model.services.AuthService;
import backend.softdle.rest.dtos.JwtTokenDto;
import backend.softdle.rest.dtos.LoginRequestDto;
import backend.softdle.rest.dtos.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public JwtTokenDto login(@RequestBody LoginRequestDto loginRequestDto){
        return  authService.login(loginRequestDto);
    }
    @PostMapping("/register")
    public JwtTokenDto register(@RequestBody RegisterRequestDto registerRequestDto){
        return  authService.register(registerRequestDto);
    }

}
