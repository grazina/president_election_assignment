package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Region;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.dto.*;
import lt.gimbutiene.presidentElection.service.CandidateService;
import lt.gimbutiene.presidentElection.service.RegionService;
import lt.gimbutiene.presidentElection.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoterFacade {
    private static final double WINNER_THRESHOLD = 0.5;

    @Autowired
    private VoterService voterService;

    @Autowired
    private CandidateConverter candidateConverter;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private RegionService regionService;

    public void addVote(final VoteDto voteDto) {
        voterService.addVote(voteDto.getVoterId(), voteDto.getSelectedCandidateId());
    }

    public ElectionResultsByCandidateDto getElectionResultsByCandidate() {
        final List<Candidate> candidates = candidateService.getAllCandidates();
        final List<Voter> voters = voterService.getAllVoters();
        final List<Voter> activeVoters = filterActiveVoters(voters);

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
        final List<Voter> activeVotersInRegion = filterActiveVoters(votersInRegion);

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

    public WinnerDto getElectionWinner() {
        final List<Candidate> top2Candidates = candidateService.getAllCandidates().stream()
                .sorted(Comparator.comparingInt((Candidate c) -> c.getVoters().size()).reversed()).limit(2).collect(Collectors.toList());

        final Candidate firstCandidate = top2Candidates.get(0);
        final Candidate secondCandidate = top2Candidates.get(1);

        final List<Voter> voters = voterService.getAllVoters();
        final List<Voter> activeVoters = filterActiveVoters(voters);

        final WinnerDto winnerDto = new WinnerDto();
        winnerDto.setTotalVotersCount((long) voters.size());
        winnerDto.setActiveVotersCount((long) activeVoters.size());
        winnerDto.setFirstCandidate(new CandidateResultsDto(candidateConverter.convert(firstCandidate), (long) firstCandidate.getVoters().size()));
        winnerDto.setSingleWinner(isSingleWinner(firstCandidate, activeVoters.size()));
        if (!winnerDto.isSingleWinner()) {
            winnerDto.setSecondCandidate(new CandidateResultsDto(candidateConverter.convert(secondCandidate), (long) secondCandidate.getVoters().size()));
        }
        return winnerDto;
    }

    private boolean isSingleWinner(final Candidate topCandidate, final int votersCount) {
        return topCandidate.getVoters().size() / (double) votersCount > WINNER_THRESHOLD;
    }

    private List<Voter> filterActiveVoters(final List<Voter> voters) {
        return voters.stream().filter(voter -> voter.getSelectedCandidate() != null).collect(Collectors.toList());
    }

    public void setVoterService(final VoterService voterService) {
        this.voterService = voterService;
    }

    public void setCandidateConverter(final CandidateConverter candidateConverter) {
        this.candidateConverter = candidateConverter;
    }

    public void setCandidateService(final CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    public void setRegionService(final RegionService regionService) {
        this.regionService = regionService;
    }
}
