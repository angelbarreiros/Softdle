package backend.softdle.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "\"user\"")
public class User {

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @Id
    private Long id;

    private String image;

}
