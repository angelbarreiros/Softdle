package backend.softdle.rest.controllers;


import backend.softdle.model.entities.Wins;
import backend.softdle.model.services.Block;
import backend.softdle.model.services.WinsService;
import backend.softdle.rest.dtos.BlockDto;
import backend.softdle.rest.dtos.WinsDto;
import backend.softdle.rest.dtos.WinsHistoryConversor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private WinsService winsService;
    @GetMapping("/history")
    public BlockDto<WinsDto> getHistory(@RequestAttribute String username,
                                        @RequestParam(defaultValue="0") int page){
        Block<Wins> winsBlock=winsService.winsHistory(username,page, Wins.HISTORY_SIZE);
        return new BlockDto<>(WinsHistoryConversor.toWinsDtos(winsBlock.getItems()),winsBlock.getExistMoreItems());
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download() throws IOException {

        InputStream in = getClass()
                .getResourceAsStream("/images/imagen.jpg");
        byte[] xd = IOUtils.toByteArray(in);
        return Base64.getEncoder().encode(xd);


    }
}
