package backend.softdle.rest.dtos;

import backend.softdle.model.entities.Wins;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class WinsHistoryConversor {
    public static WinsDto toWinsDto(Wins wins){
        return new WinsDto(toMillis(wins.getDate()),wins.getNumberOfAttempts());
    }
    public static List<WinsDto> toWinsDtos(List<Wins> winsList){
        List<WinsDto> purchaseHistoryDtos = new ArrayList<>();
        for (Wins item: winsList){
            purchaseHistoryDtos.add(toWinsDto(item));
        }
        return purchaseHistoryDtos;
    }
    private  static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
