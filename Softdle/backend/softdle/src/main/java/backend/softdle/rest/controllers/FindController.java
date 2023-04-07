package backend.softdle.rest.controllers;

import backend.softdle.model.exceptions.LanguageNotFoundException;
import backend.softdle.model.services.LanguageService;
import backend.softdle.rest.dtos.LanguageResponseDto;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static backend.softdle.rest.dtos.LanguageResponseConversor.toLanguageResponseDto;

@RestController
@RequestMapping("/api")
public class FindController {
    @Autowired
    private LanguageService languageService;
    @GetMapping("/find")
    public LanguageResponseDto find(@PathParam("name") String name) throws LanguageNotFoundException {
      return toLanguageResponseDto(languageService.tryToFind(name));
    }
}
