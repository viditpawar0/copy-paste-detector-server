package org.lsrv.copypastedetector.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@ToString
@EqualsAndHashCode
public class Session {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "session")
    private List<Snippet> snippets;

    @Column(nullable = false, updatable = false, insertable = false)
    private LocalDateTime endsAt;
}
