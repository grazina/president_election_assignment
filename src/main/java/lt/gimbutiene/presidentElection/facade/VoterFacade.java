package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Region;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.dto.CandidateResultsDto;
import lt.gimbutiene.presidentElection.dto.ElectionResultsByCandidateDto;
import lt.gimbutiene.presidentElection.dto.RegionResultsDto;
import lt.gimbutiene.presidentElection.service.CandidateService;
import lt.gimbutiene.presidentElection.service.RegionService;
import lt.gimbutiene.presidentElection.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoterFacade {
    @Autowired
    private VoterService voterService;

    @Autowired
    private CandidateConverter candidateConverter;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private RegionService regionService;

    public ElectionResultsByCandidateDto getElectionResultsByCandidate() {
        final List<Candidate> candidates = candidateService.getAllCandidates();
        final List<Voter> voters = voterService.getAllVoters();
        final List<Voter> activeVoters = voters.stream().filter(voter -> voter.getSelectedCandidate() != null).collect(Collectors.toList());

        final List<CandidateResultsDto> candidateResultsDtos = candidates.stream()
                .map(candidate -> new CandidateResultsDto(candidateConverter.convert(candidate), (long) candidate.getVoters().size())).collect(Collectors.toList());
        return new ElectionResultsByCandidateDto((long) activeVoters.size(), (long) voters.size(), candidateResultsDtos);
    }

    public List<RegionResultsDto> getElectionResultsByRegion() {
        final List<Candidate> candidates = candidateService.getAllCandidates();
        final List<Voter> voters = voterService.getAllVoters();
        return regionService.getAllRegions().stream().map(region -> makeRegionResultsDto(region, candidates, voters)).collect(Collectors.toList());
    }

    private RegionResultsDto makeRegionResultsDto(final Region region, final List<Candidate> candidates, final List<Voter> voters) {
        final List<Voter> votersInRegion = voters.stream().filter(voter -> region.getId().equals(voter.getRegion().getId())).collect(Collectors.toList());
        final List<Voter> activeVotersInRegion = votersInRegion.stream().filter(voter -> voter.getSelectedCandidate() != null).collect(Collectors.toList());

        final List<CandidateResultsDto> candidateResultsInRegion = candidates.stream()
                .map(candidate -> new CandidateResultsDto(candidateConverter.convert(candidate), getCandidateVotersInRegionCount(candidate, region)))
                .collect(Collectors.toList());

        final ElectionResultsByCandidateDto resultsByCandidate =
                new ElectionResultsByCandidateDto((long) activeVotersInRegion.size(), (long) votersInRegion.size(), candidateResultsInRegion);

        return new RegionResultsDto(region.getId(), region.getName(), resultsByCandidate);
    }

    private long getCandidateVotersInRegionCount(final Candidate candidate, final Region region) {
        final List<Voter> candidateVotersInRegion = candidate.getVoters().stream()
                .filter(voter -> region.getId().equals(voter.getRegion().getId())).collect(Collectors.toList());
        return candidateVotersInRegion.size();
    }

    public void setVoterService(final VoterService voterService) {
        this.voterService = voterService;
    }

    public void setCandidateConverter(final CandidateConverter candidateConverter) {
        this.candidateConverter = candidateConverter;
    }
}
