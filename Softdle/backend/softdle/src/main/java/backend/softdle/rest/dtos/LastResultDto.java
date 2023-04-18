package backend.softdle.rest.dtos;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LastResultDto {
    private String language;
    private int numberOfAttempts;

}
