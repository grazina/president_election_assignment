package lt.gimbutiene.presidentElection.dto;

public class VoteDto {
    private Long voterId;
    private Long selectedCandidateId;

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(final Long voterId) {
        this.voterId = voterId;
    }

    public Long getSelectedCandidateId() {
        return selectedCandidateId;
    }

    public void setSelectedCandidateId(final Long selectedCandidateId) {
        this.selectedCandidateId = selectedCandidateId;
    }
}
