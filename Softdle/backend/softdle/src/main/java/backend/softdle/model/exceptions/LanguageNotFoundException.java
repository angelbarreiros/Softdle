package backend.softdle.model.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LanguageNotFoundException extends Exception {
    private String name;

}
