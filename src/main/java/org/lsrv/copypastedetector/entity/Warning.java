package org.lsrv.copypastedetector.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Setter
@Getter
@Entity
@ToString
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
public class Warning {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private Severity severity;

    @CreatedDate
    @Column
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
    enum Severity { HIGH, MEDIUM, LOW }
}
