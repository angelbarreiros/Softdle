package backend.softdle.rest.dtos;


import backend.softdle.model.services.LanguageResponse;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class LanguageResponseDto {

    private Boolean isTheOne;
    private Boolean typeType;
    private Boolean compilingType;
    private LanguageResponse.DateType date;
    private Boolean creator;
    private Boolean paradigm;
    private Boolean purpose;
    private LanguageResponse.DateType numberOfJobs;


}
