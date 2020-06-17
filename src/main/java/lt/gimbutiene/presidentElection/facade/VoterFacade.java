package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Region;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.dto.CandidateResultsDto;
import lt.gimbutiene.presidentElection.dto.ElectionResultsByCandidateDto;
import lt.gimbutiene.presidentElection.dto.RegionResultsDto;
import lt.gimbutiene.presidentElection.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VoterFacade {
    @Autowired
    private VoterService voterService;

    @Autowired
    private CandidateConverter candidateConverter;

    public ElectionResultsByCandidateDto getElectionResultsByCandidate() {
        return obtainResultsFromVotersList(voterService.getAllVoters());
    }

    private ElectionResultsByCandidateDto obtainResultsFromVotersList(final List<Voter> allVoters) {
        final List<Voter> activeVoters = allVoters.stream().filter(voter -> voter.getSelectedCandidate() != null).collect(Collectors.toList());
        final Map<Candidate, Long> candidateVotesMap = activeVoters.stream().collect(Collectors.groupingBy(Voter::getSelectedCandidate, Collectors.counting()));
        return makeElectionResultsByCandidateDto(candidateVotesMap, activeVoters.size(), allVoters.size());
    }

    private ElectionResultsByCandidateDto makeElectionResultsByCandidateDto(final Map<Candidate, Long> candidateVotesMap, final long activeVotersCount, final long allVotersCount) {
        final List<CandidateResultsDto> candidateResultsDtos = candidateVotesMap.entrySet().stream()
                .map(this::makeCandidateResultsDto).collect(Collectors.toList());

        final ElectionResultsByCandidateDto electionResultsByCandidateDto = new ElectionResultsByCandidateDto();
        electionResultsByCandidateDto.setCandidateResults(candidateResultsDtos);
        electionResultsByCandidateDto.setActiveVotersCount(activeVotersCount);
        electionResultsByCandidateDto.setTotalVotersCount(allVotersCount);
        return electionResultsByCandidateDto;
    }

    private CandidateResultsDto makeCandidateResultsDto(final Map.Entry<Candidate, Long> candidateEntry) {
        final CandidateResultsDto candidateResultsDto = new CandidateResultsDto();
        candidateResultsDto.setCandidate(candidateConverter.convert(candidateEntry.getKey()));
        candidateResultsDto.setCandidateVotesCount(candidateEntry.getValue());
        return candidateResultsDto;
    }

    public List<RegionResultsDto> getElectionResultsByRegion() {
        final List<Voter> allVoters = voterService.getAllVoters();
        final Map<Region, List<Voter>> votersByRegionMap =
                allVoters.stream().collect(Collectors.groupingBy(Voter::getRegion));

        return votersByRegionMap.entrySet().stream()
                .map(this::makeRegionResultsDto).collect(Collectors.toList());
    }

    private RegionResultsDto makeRegionResultsDto(final Map.Entry<Region, List<Voter>> regionEntry) {
        final RegionResultsDto regionalCandidateResultDto = new RegionResultsDto();
        regionalCandidateResultDto.setRegionId(regionEntry.getKey().getId());
        regionalCandidateResultDto.setName(regionEntry.getKey().getName());
        regionalCandidateResultDto.setResultsByCandidate(obtainResultsFromVotersList(regionEntry.getValue()));
        return regionalCandidateResultDto;
    }

    public void setVoterService(final VoterService voterService) {
        this.voterService = voterService;
    }

    public void setCandidateConverter(final CandidateConverter candidateConverter) {
        this.candidateConverter = candidateConverter;
    }
}
