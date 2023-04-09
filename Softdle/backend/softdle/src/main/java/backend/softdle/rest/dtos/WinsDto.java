package backend.softdle.rest.dtos;

import jakarta.persistence.Table;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder

public class WinsDto {
        private Long date;
        private int numberOfAttempts;
}
