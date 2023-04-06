package backend.softdle.model.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


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
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @Id
    private Long id;
    private LocalDate date;
    private int numberOfAttempts;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "winsId")
    private User user;
}
