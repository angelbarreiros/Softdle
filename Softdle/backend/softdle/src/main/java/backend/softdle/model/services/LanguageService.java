package backend.softdle.model.services;

import backend.softdle.model.exceptions.LanguageNotFoundException;

import java.util.List;

public interface LanguageService {
     List<LanguageSelection> getNamesAndImages();
     LanguageResponse tryToFind(String name) throws LanguageNotFoundException;
     void changeDiaryLanguage() throws LanguageNotFoundException;
}
