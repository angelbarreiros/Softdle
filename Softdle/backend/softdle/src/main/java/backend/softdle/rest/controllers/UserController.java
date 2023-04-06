package backend.softdle.rest.controllers;


import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@RequestMapping("/users")
public class UserController {

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download() throws IOException {

        InputStream in = getClass()
                .getResourceAsStream("/images/imagen.jpg");
        byte[] xd = IOUtils.toByteArray(in);
        return Base64.getEncoder().encode(xd);


    }
}
