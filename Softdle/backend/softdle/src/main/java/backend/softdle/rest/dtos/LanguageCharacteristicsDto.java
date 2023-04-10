package backend.softdle.rest.dtos;

import backend.softdle.model.entities.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class LanguageCharacteristicsDto {
    private byte[] imagePath;
    private Language.TypingType typeType;
    @Enumerated(EnumType.STRING)
    private Language.CompilingType compilingType;
    private Integer date;
    private String creator;
    @Enumerated(EnumType.STRING)
    private Language.Paradigm paradigm;
    private String purpose;
    private Integer numberOfJobs;
}
