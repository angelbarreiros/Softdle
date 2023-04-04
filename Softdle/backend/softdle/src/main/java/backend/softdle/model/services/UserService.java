package backend.softdle.model.services;

import backend.softdle.model.entities.User;
import backend.softdle.model.entities.UserDao;
import ch.qos.logback.core.testUtil.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void addUser(User user){
        userDao.save(user);
    }
    public void downloadFile(MultipartFile file,Long id) throws IOException {
        if (!file.isEmpty()){
            Optional<User> optionalUser=userDao.findById(id);
            if (optionalUser.isPresent()){
                Path path= Paths.get("/home/angel/Softdle/Softdle/backend/softdle/images/"+Math.random()+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path);
                optionalUser.get().setImage(file.getOriginalFilename());
            }
        }


    }
}
