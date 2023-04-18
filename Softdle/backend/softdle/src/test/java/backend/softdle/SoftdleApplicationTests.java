package backend.softdle;

import backend.softdle.model.entities.*;
import backend.softdle.model.exceptions.LanguageNotFoundException;
import backend.softdle.model.exceptions.UserAlreadyExistsException;
import backend.softdle.model.services.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class SoftdleApplicationTests {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LanguageDao languageDao;
	@Autowired
	private AuthService authService;
	@Autowired
	private WinsService winsService;
	@Autowired
	private LanguageService languageService;
	@Autowired
	private ScheduledTaskService scheduledTaskService;

	@Test
	public void authTest() throws UserAlreadyExistsException {
		User user = User.builder()
				.firstname("angel")
				.lastname("barreiros")
				.username("angel")
				.password("pass")
				.role(User.RoleType.USER)
				.isPlayed(false)
				.build();
		authService.register(user);
		assertThrows(UserAlreadyExistsException.class, () -> authService.register(user));
		assertEquals(userDao.findByUsername(user.getUsername()).get().getUsername(), user.getUsername());
		authService.login(user);
		assertEquals(user.getIsPlayed(),false);
		winsService.addWin("angel",2,"java");
		assertEquals(userDao.findByUsername("angel").get().getIsPlayed(), true);
		scheduledTaskService.usersCanPlay();
		assertEquals(userDao.findByUsername("angel").get().getIsPlayed(),false);

		scheduledTaskService.usersCanPlay();
		user.setPassword("sdadsadsa");
		assertThrows(BadCredentialsException.class,()-> authService.login(user));

	}

	@Test
	public void winsTest() throws UserAlreadyExistsException {
		User user = User.builder()
				.firstname("angel")
				.lastname("barreiros")
				.username("angel")
				.password("pass")
				.role(User.RoleType.USER)
				.isPlayed(false)
				.build();

		Block<Wins> winsBlock1 =winsService.winsHistory("angel",0,Wins.HISTORY_SIZE);
		assertEquals(winsBlock1.getItems().size(),0);

		authService.register(user);
		winsService.addWin("angel",2,"java");
		winsService.addWin("angel",1,"java");
		winsService.addloose("angel",10,"java");

		Block<Wins> winsBlock =winsService.winsHistory("angel",0,Wins.HISTORY_SIZE);
		assertEquals(winsBlock.getItems().size(),3);

		Block<Wins> winsBlock2 =winsService.winsHistory("angel",1,Wins.HISTORY_SIZE);
		assertEquals(winsBlock2.getItems().size(),0);
	}

	@Test
	public void languageTest() throws LanguageNotFoundException {
		assertThrows(LanguageNotFoundException.class,()->languageService.tryToFind(""));
		Language language = Language.builder()
				.name("python")
				.imagePath("")
				.isTheOne(true)
				.numberOfJobs(100)
				.compilingType(Language.CompilingType.COMPILED)
				.purpose("dsadsa")
				.date(1990)
				.paradigm(Language.Paradigm.FUNCTIONAL)
				.typeType(Language.TypingType.DYNAMIC)
				.creator("dadsas")
				.build();
		Language language2 = Language.builder()
				.name("c")
				.imagePath("")
				.isTheOne(false)
				.numberOfJobs(100)
				.compilingType(Language.CompilingType.COMPILED)
				.creator("fdsads")
				.paradigm(Language.Paradigm.MULTI)
				.typeType(Language.TypingType.DYNAMIC)
				.date(1990)
				.purpose("adsadsadsa")
				.build();
		languageDao.save(language);
		languageDao.save(language2);
		LanguageResponse languageResponse=new LanguageResponse(true,true,true, LanguageResponse.DateType.Perfect
				,true,true,true, LanguageResponse.DateType.Perfect);
		LanguageResponse languageResponse2=new LanguageResponse(false,true,true, LanguageResponse.DateType.Perfect
				,false,false,false, LanguageResponse.DateType.Perfect);
		assertEquals(languageService.tryToFind("python"),languageResponse);
		assertEquals(languageService.tryToFind("c"),languageResponse2);


	}

	@Test
	void getNamesAndImagesTest(){
		Language language = Language.builder()
				.name("python")
				.imagePath("")
				.isTheOne(true)
				.numberOfJobs(100)
				.compilingType(Language.CompilingType.COMPILED)
				.purpose("dsadsa")
				.date(1990)
				.paradigm(Language.Paradigm.FUNCTIONAL)
				.typeType(Language.TypingType.DYNAMIC)
				.creator("dadsas")
				.build();
		Language language2 = Language.builder()
				.name("c")
				.imagePath("")
				.isTheOne(false)
				.numberOfJobs(100)
				.compilingType(Language.CompilingType.COMPILED)
				.creator("fdsads")
				.paradigm(Language.Paradigm.MULTI)
				.typeType(Language.TypingType.DYNAMIC)
				.date(1990)
				.purpose("adsadsadsa")
				.build();
		languageDao.save(language);
		languageDao.save(language2);
		List<LanguageSelection> l=languageService.getNamesAndImages();
		LanguageSelection languageSelection= new LanguageSelection("python","");
		LanguageSelection languageSelection2= new LanguageSelection("c","");
		assertEquals(l.get(0),languageSelection);
		assertEquals(l.get(1),languageSelection2);
	}



}
