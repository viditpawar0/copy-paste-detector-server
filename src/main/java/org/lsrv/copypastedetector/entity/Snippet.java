package org.lsrv.copypastedetector.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
@EqualsAndHashCode
public class Snippet {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private SnippetType type;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, insertable = false, updatable = false)
    private Instant createdAt;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    @ManyToOne
    private Session session;

    @JsonProperty("session")
    public void setSessionById(Long id) {
        this.session = new Session();
        this.session.setId(id);
    }

    enum SnippetType {
        COPIED, PASTED
    }
}
