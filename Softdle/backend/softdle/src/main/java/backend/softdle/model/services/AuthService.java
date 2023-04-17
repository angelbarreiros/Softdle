package backend.softdle.model.services;

import backend.softdle.model.entities.User;
import backend.softdle.model.exceptions.UserAlreadyExistsException;
import backend.softdle.rest.dtos.JwtTokenDto;

public interface AuthService {
     JwtTokenDto register(User user) throws UserAlreadyExistsException;
     JwtTokenDto login(User user);
     JwtTokenDto refresh(String usename);
     Boolean verify(String usename);
}
