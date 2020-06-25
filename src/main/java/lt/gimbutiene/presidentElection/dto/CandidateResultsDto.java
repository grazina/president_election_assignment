package lt.gimbutiene.presidentElection.dto;

public class CandidateResultsDto {
    private CandidateBaseDto candidate;
    private Long candidateVotesCount;

    public CandidateResultsDto() {
    }

    public CandidateResultsDto(final CandidateBaseDto candidate, final Long candidateVotesCount) {
        this.candidate = candidate;
        this.candidateVotesCount = candidateVotesCount;
    }

    public CandidateBaseDto getCandidate() {
        return candidate;
    }

    public void setCandidate(final CandidateInfoDto candidate) {
        this.candidate = candidate;
    }

    public Long getCandidateVotesCount() {
        return candidateVotesCount;
    }

    public void setCandidateVotesCount(final Long candidateVotesCount) {
        this.candidateVotesCount = candidateVotesCount;
    }
}
