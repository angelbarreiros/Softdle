package backend.softdle.rest.dtos;

import backend.softdle.model.entities.Wins;

import java.util.ArrayList;
import java.util.List;

public class WinsHistoryConversor {
    public static WinsDto toWinsDto(Wins wins){
        return new WinsDto(wins.getDate(),wins.getNumberOfAttempts());
    }
    public static List<WinsDto> toWinsDtos(List<Wins> winsList){
        List<WinsDto> purchaseHistoryDtos = new ArrayList<>();
        for (Wins item: winsList){
            purchaseHistoryDtos.add(toWinsDto(item));
        }
        return purchaseHistoryDtos;
    }
}
