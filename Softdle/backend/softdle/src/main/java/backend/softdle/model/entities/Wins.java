package backend.softdle.model.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Builder
@Entity
public class Wins {
    public static final int HISTORY_SIZE=10;
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "wins_id_seq")
    @SequenceGenerator(name = "wins_id_seq", sequenceName = "wins_id_seq", allocationSize = 1)
    @Id
    private Long id;
    private LocalDateTime date;
    private int numberOfAttempts;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}

