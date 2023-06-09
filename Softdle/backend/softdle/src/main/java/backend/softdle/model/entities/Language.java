package backend.softdle.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "language_")
public class Language {
    public enum TypingType {DYNAMIC,STATIC,BOTH,NO}
    public enum CompilingType {COMPILED,INTERPRETED,BOTH,NO}
    public enum Paradigm {FUNCTIONAL,OOP,MULTI, IMPERATIVE,PROCEDURAL}

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "language__id_seq")
    @SequenceGenerator(name = "language__id_seq", sequenceName = "language__id_seq", allocationSize = 1)
    @Id
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private TypingType typeType;
    @Enumerated(EnumType.STRING)
    private CompilingType compilingType;
    private Integer date;
    private String creator;
    @Enumerated(EnumType.STRING)
    private Paradigm paradigm;
    private String purpose;
    private Integer numberOfJobs;
    private String imagePath;
    @Column(nullable = false)
    private Boolean isTheOne;


}
