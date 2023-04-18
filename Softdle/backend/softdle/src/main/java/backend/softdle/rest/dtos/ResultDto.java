package backend.softdle.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResultDto {
    private Boolean isWin;
    private Integer attempts;
    private String language;
}
