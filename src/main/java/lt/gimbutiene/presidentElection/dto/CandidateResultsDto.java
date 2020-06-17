package lt.gimbutiene.presidentElection.dto;

public class CandidateResultsDto {
    private CandidateDto candidate;
    private Long candidateVotesCount;

    public CandidateResultsDto() {
    }

    public CandidateResultsDto(final CandidateDto candidate, final Long candidateVotesCount) {
        this.candidate = candidate;
        this.candidateVotesCount = candidateVotesCount;
    }

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
}
