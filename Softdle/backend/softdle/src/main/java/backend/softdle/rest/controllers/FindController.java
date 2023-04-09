package backend.softdle.rest.controllers;

import backend.softdle.model.exceptions.LanguageNotFoundException;
import backend.softdle.model.services.LanguageSelection;
import backend.softdle.model.services.LanguageService;
import backend.softdle.rest.dtos.LanguageResponseDto;
import backend.softdle.rest.dtos.LanguageSelectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static backend.softdle.rest.dtos.LanguageResponseConversor.toLanguageResponseDto;

@RestController
@RequestMapping("/api")
public class FindController {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ResourceLoader resourceLoader;
    @PostMapping("/find")
    public LanguageResponseDto find(@RequestParam String name) throws LanguageNotFoundException {
      return toLanguageResponseDto(languageService.tryToFind(name));
    }
    @GetMapping(value = "/languages")
    public List<LanguageSelectionDto> getLanguages() throws IOException {
        List<LanguageSelectionDto> images = new ArrayList<>();
        for (LanguageSelection languageSelection : languageService.getNamesAndImages()){
            Resource resource = resourceLoader.getResource("classpath:"+languageSelection.getImagePath());
            byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
            LanguageSelectionDto image = new LanguageSelectionDto(languageSelection.getName(), bytes);
            images.add(image);
        }
        return images;
    }

}
