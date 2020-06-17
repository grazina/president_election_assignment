package lt.gimbutiene.presidentElection.dto;

public class RegionResultsDto {
    private Long regionId;
    private String name;
    private ElectionResultsByCandidateDto resultsByCandidate;

    public RegionResultsDto() {
    }

    public RegionResultsDto(final Long regionId, final String name, final ElectionResultsByCandidateDto resultsByCandidate) {
        this.regionId = regionId;
        this.name = name;
        this.resultsByCandidate = resultsByCandidate;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(final Long regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ElectionResultsByCandidateDto getResultsByCandidate() {
        return resultsByCandidate;
    }

    public void setResultsByCandidate(final ElectionResultsByCandidateDto resultsByCandidate) {
        this.resultsByCandidate = resultsByCandidate;
    }
}
