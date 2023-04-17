package backend.softdle.model.services;


import backend.softdle.model.entities.UserDao;
import backend.softdle.model.exceptions.LanguageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskService {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserDao userDao;

    @Scheduled(cron = "0 0 0 * * *")
    public void execute() throws LanguageNotFoundException {
        languageService.changeDiaryLanguage();


    }


    @Scheduled(cron = "0 0 0 * * *")
    public void usersCanPlay()  {
        userDao.findAll().forEach(item -> item.setIsPlayed(false));
    }
}
