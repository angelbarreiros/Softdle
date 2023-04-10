package backend.softdle.rest.dtos;

import backend.softdle.model.entities.Language;

public class LanguageCharacteristicConversor {
    public static LanguageCharacteristicsDto toLanguageCharacteristicsDto(Language language){
        return new LanguageCharacteristicsDto(null,language.getTypeType(),language.getCompilingType(),language.getDate(),
                language.getCreator(),language.getParadigm(),language.getPurpose(),language.getNumberOfJobs());
    }
}
