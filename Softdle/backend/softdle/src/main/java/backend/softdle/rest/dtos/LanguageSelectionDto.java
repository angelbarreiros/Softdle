package backend.softdle.rest.dtos;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LanguageSelectionDto {
    private String name;
    private byte[] imagePath;


}
