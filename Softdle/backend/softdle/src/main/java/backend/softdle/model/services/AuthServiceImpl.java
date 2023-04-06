package backend.softdle.model.services;


import backend.softdle.model.entities.User;
import backend.softdle.model.entities.UserDao;
import backend.softdle.model.exceptions.UserAlreadyExistsException;
import backend.softdle.rest.common.JwtService;
import backend.softdle.rest.dtos.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtTokenDto login(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        User user1 = userDao.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

           return JwtTokenDto.builder()
                   .token(jwtService.generateToken(user1))
                   .build();



    }
    @Transactional
    public JwtTokenDto register(User user) throws UserAlreadyExistsException {
        Optional<User> optionalUser = userDao.findByUsername(user.getUsername());
        if (optionalUser.isEmpty()){
            User user1= User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .streak(0)
                    .role(User.RoleType.USER)
                    .build();
            User use2r =userDao.save(user1);

            return JwtTokenDto.builder()
                    .token(jwtService.generateToken(use2r))
                    .build();
        }
        throw new UserAlreadyExistsException();

    }
}
