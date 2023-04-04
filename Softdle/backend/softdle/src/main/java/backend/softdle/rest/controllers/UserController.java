package backend.softdle.rest.controllers;


import backend.softdle.model.entities.User;
import backend.softdle.model.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping( "/login")
    public User addUser(@RequestBody User user){
        userService.addUser(user);
        return user;

    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String  uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") Long id) throws IOException {
        userService.downloadFile(file,id);
        return "Hola Mundo";



    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download() throws IOException {

        InputStream in = getClass()
                .getResourceAsStream("/images/imagen.jpg");
        byte[] xd = IOUtils.toByteArray(in);
        return Base64.getEncoder().encode(xd);


    }
}
