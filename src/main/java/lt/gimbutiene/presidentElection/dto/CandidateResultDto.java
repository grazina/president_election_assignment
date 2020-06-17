package lt.gimbutiene.presidentElection.dto;

public class CandidateResultDto {
    private CandidateDto candidate;
    private Long candidateVotesCount;
    private Long activeVotersCount;
    private Long totalVotersCount;

    public CandidateDto getCandidate() {
        return candidate;
    }

    public void setCandidate(final CandidateDto candidate) {
        this.candidate = candidate;
    }

    public Long getCandidateVotesCount() {
        return candidateVotesCount;
    }

    public void setCandidateVotesCount(final Long candidateVotesCount) {
        this.candidateVotesCount = candidateVotesCount;
    }

    public Long getActiveVotersCount() {
        return activeVotersCount;
    }

    public void setActiveVotersCount(final Long activeVotersCount) {
        this.activeVotersCount = activeVotersCount;
    }

    public Long getTotalVotersCount() {
        return totalVotersCount;
    }

    public void setTotalVotersCount(final Long totalVotersCount) {
        this.totalVotersCount = totalVotersCount;
    }
}
