package lt.gimbutiene.presidentElection.domain;

import javax.persistence.*;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_candidate_id")
    private Candidate selectedCandidate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Candidate getSelectedCandidate() {
        return selectedCandidate;
    }

    public void setSelectedCandidate(final Candidate selectedCandidate) {
        this.selectedCandidate = selectedCandidate;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(final Region region) {
        this.region = region;
    }
}
