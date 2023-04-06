package backend.softdle.model.services;

import backend.softdle.model.entities.Language;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
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
