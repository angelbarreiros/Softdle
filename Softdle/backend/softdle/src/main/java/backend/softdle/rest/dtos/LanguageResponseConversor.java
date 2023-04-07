package backend.softdle.rest.dtos;

import backend.softdle.model.services.LanguageResponse;

public class LanguageResponseConversor {
    public static LanguageResponseDto toLanguageResponseDto(LanguageResponse languageResponse){
        return  new LanguageResponseDto(
                languageResponse.getIsTheOne(),
                languageResponse.getTypeType(),
                languageResponse.getCompilingType(),
                languageResponse.getDate(),
                languageResponse.getCreator(),
                languageResponse.getParadigm(),
                languageResponse.getPurpose(),
                languageResponse.getNumberOfJobs()


        );
    }
}
