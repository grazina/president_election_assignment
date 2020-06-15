package lt.gimbutiene.presidentElection.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long number;
    private String agenda;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(final Long number) {
        this.number = number;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(final String agenda) {
        this.agenda = agenda;
    }
}
