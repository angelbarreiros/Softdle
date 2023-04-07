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
@EqualsAndHashCode
@Builder
@Entity
public class Language {
    public enum TypingType {DINAMIC,STATIC}
    public enum CompilingType {COMPILED,INTERPRETED,BOTH}
    public enum Paradigm {FUNCTIONAL,OOP,MULTIPARADIGM,IMPERACTIVE,PROCEDIMENTAL}

    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 1)
    @Id
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    private TypingType typeType;
    private CompilingType compilingType;
    private LocalDate date;
    private String creator;
    private Paradigm paradigm;
    private String purpose;
    private Integer numberOfJobs;
    private String imagePath;
    @Column(unique = true,nullable = false)
    private Boolean isTheOne;


}
