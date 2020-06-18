package lt.gimbutiene.presidentElection.dto;

public class WinnerDto {
    private boolean singleWinner;
    private Long activeVotersCount;
    private Long totalVotersCount;
    private CandidateResultsDto firstCandidate;
    private CandidateResultsDto secondCandidate;

    public boolean isSingleWinner() {
        return singleWinner;
    }

    public void setSingleWinner(final boolean singleWinner) {
        this.singleWinner = singleWinner;
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

    public CandidateResultsDto getFirstCandidate() {
        return firstCandidate;
    }

    public void setFirstCandidate(final CandidateResultsDto firstCandidate) {
        this.firstCandidate = firstCandidate;
    }

    public CandidateResultsDto getSecondCandidate() {
        return secondCandidate;
    }

    public void setSecondCandidate(final CandidateResultsDto secondCandidate) {
        this.secondCandidate = secondCandidate;
    }
}
