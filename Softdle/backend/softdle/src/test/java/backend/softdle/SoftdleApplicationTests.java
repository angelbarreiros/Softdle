package backend.softdle;

import backend.softdle.model.entities.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoftdleApplicationTests {

	@Autowired
	private UserDao userDao;
	@Test
	void contextLoads() {

	}

}
