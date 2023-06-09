package backend.softdle.model.services;


import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class LanguageResponse {
    public enum DateType {Less,More,Perfect}
    private Boolean isTheOne;
    private Boolean typeType;
    private Boolean compilingType;
    private DateType date;
    private Boolean creator;
    private Boolean paradigm;
    private Boolean purpose;
    private DateType numberOfJobs;


}
