package backend.softdle.model.services;


import backend.softdle.model.entities.User;
import backend.softdle.model.entities.UserDao;
import backend.softdle.rest.common.JwtService;
import backend.softdle.rest.dtos.JwtTokenDto;
import backend.softdle.rest.dtos.LoginRequestDto;
import backend.softdle.rest.dtos.RegisterRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtTokenDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return JwtTokenDto.builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    public JwtTokenDto register(RegisterRequestDto request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(User.RoleType.USER)
                .build();
        userRepository.save(user);
        return JwtTokenDto.builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
