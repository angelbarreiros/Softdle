package backend.softdle.model.services;

import backend.softdle.model.exceptions.LanguageNotFoundException;

public interface LanguageService {
     LanguageResponse tryToFind(String name) throws LanguageNotFoundException;
     void changeDiaryLanguage() throws LanguageNotFoundException;
}
