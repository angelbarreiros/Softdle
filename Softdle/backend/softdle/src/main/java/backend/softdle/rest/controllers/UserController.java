package backend.softdle.rest.controllers;


import backend.softdle.model.entities.Wins;
import backend.softdle.model.services.Block;
import backend.softdle.model.services.WinsService;
import backend.softdle.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/result")
    public void addResult(@RequestAttribute String username, @RequestBody ResultDto resultDto){
     if (resultDto.getIsWin()){
         winsService.addWin(username,resultDto.getAttempts(),resultDto.getLanguage());

     }
     else {
         winsService.addloose(username,resultDto.getAttempts(),resultDto.getLanguage());
     }
    }

    @GetMapping("/isPlayed")
    public Boolean addResult(@RequestAttribute String username){
        return winsService.isPlayed(username);

    }
    @GetMapping("/lastResult")
    public LastResultDto lastResult(@RequestAttribute String username){
        Wins wins=winsService.winsHistory(username,0, Wins.HISTORY_SIZE).getItems().get(0);
        return new LastResultDto(wins.getLanguage(),wins.getNumberOfAttempts());

    }



}
