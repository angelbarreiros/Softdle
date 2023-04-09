package backend.softdle.model.services;

import lombok.*;

@NoArgsConstructor
@ToString
@Builder
@Getter
@EqualsAndHashCode
public class LanguageSelection {
    private String name;
    private String imagePath;

    public LanguageSelection(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }
}
