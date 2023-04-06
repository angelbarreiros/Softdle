package backend.softdle.model.services;

import backend.softdle.model.entities.Language;
import backend.softdle.model.entities.LanguageDao;
import backend.softdle.model.exceptions.LanguageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageDao languageDao;
    public LanguageResponse tryToFind(String name) throws LanguageNotFoundException {
        Language language=languageDao.findByIsTheOne(Boolean.TRUE);
        Language trylanguage=languageDao.findByName(name).orElseThrow(()-> new LanguageNotFoundException(name));
        if (language.equals(trylanguage)){
            return new LanguageResponse(true,true,true, LanguageResponse.DateType.Perfect
                    ,true,true,true, LanguageResponse.DateType.Perfect);
        }
        else{
            return compare(language,trylanguage) ;
        }

    }
    private LanguageResponse compare(Language language, Language trylangauge){
        return null;
    }

}
