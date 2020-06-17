package lt.gimbutiene.presidentElection.dto;

import java.util.List;

public class ElectionResultsByCandidateDto {
    private Long activeVotersCount;
    private Long totalVotersCount;
    private List<CandidateResultsDto> candidateResults;

    public ElectionResultsByCandidateDto() {
    }

    public ElectionResultsByCandidateDto(final Long activeVotersCount, final Long totalVotersCount, final List<CandidateResultsDto> candidateResults) {
        this.activeVotersCount = activeVotersCount;
        this.totalVotersCount = totalVotersCount;
        this.candidateResults = candidateResults;
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

    public List<CandidateResultsDto> getCandidateResults() {
        return candidateResults;
    }

    public void setCandidateResults(final List<CandidateResultsDto> candidateResults) {
        this.candidateResults = candidateResults;
    }
}
