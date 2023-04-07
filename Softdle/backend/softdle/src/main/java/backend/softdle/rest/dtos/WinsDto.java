package backend.softdle.rest.dtos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder

public class WinsDto {
        private LocalDate date;
        private int numberOfAttempts;
}
