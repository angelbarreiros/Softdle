package backend.softdle.model.services;

import backend.softdle.model.entities.Language;
import backend.softdle.model.entities.LanguageDao;
import backend.softdle.model.exceptions.LanguageNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    private LanguageDao languageDao;
    public LanguageResponse tryToFind(String name) throws LanguageNotFoundException {
        Language language=languageDao.findByIsTheOne(true);
        Language trylanguage=languageDao.findByName(name).orElseThrow(()-> new LanguageNotFoundException(name));
        if (language.equals(trylanguage)){
            return new LanguageResponse(true,true,true, LanguageResponse.DateType.Perfect
                    ,true,true,true, LanguageResponse.DateType.Perfect);
        }
        else{
            return compare(language,trylanguage) ;
        }

    }
    @Transactional
    @Override
    public void changeDiaryLanguage() throws LanguageNotFoundException {
        Random r = new Random();
        int low = 1;
        int high = 71;
        Long result = (long) (r.nextInt(high-low) + low);
        Language language=languageDao.findByIsTheOne(Boolean.TRUE);
        language.setIsTheOne(false);
        Language insertLanguage=languageDao.findById(result).orElseThrow(() -> new LanguageNotFoundException("random number has failed"));
        insertLanguage.setIsTheOne(true);
    }

    private LanguageResponse compare(Language language, Language trylanguage){
        LanguageResponse languageResponse= new LanguageResponse();
        if (trylanguage.getDate()<(language.getDate())){
                languageResponse.setDate(LanguageResponse.DateType.More);
        }
        else if (trylanguage.getDate().equals(language.getDate())){
            languageResponse.setDate(LanguageResponse.DateType.Perfect);
        }
        else {
            languageResponse.setDate(LanguageResponse.DateType.Less);
        }
        if (trylanguage.getNumberOfJobs()>language.getNumberOfJobs()){
            languageResponse.setNumberOfJobs(LanguageResponse.DateType.Less);
        }
        else if (trylanguage.getNumberOfJobs().equals(language.getNumberOfJobs())){
            languageResponse.setNumberOfJobs(LanguageResponse.DateType.Perfect);
        }
        else {
            languageResponse.setNumberOfJobs(LanguageResponse.DateType.Less);
        }
        languageResponse.setCompilingType(language.getCompilingType()==trylanguage.getCompilingType());
        languageResponse.setTypeType(language.getTypeType()==trylanguage.getTypeType());
        languageResponse.setTypeType(language.getCompilingType()==trylanguage.getCompilingType());
        languageResponse.setCreator(language.getCreator().equals(trylanguage.getCreator()));
        languageResponse.setParadigm(language.getParadigm()==trylanguage.getParadigm());
        languageResponse.setPurpose(language.getPurpose().equals(trylanguage.getPurpose()));
        languageResponse.setIsTheOne(false);


        return languageResponse;

    }

}
