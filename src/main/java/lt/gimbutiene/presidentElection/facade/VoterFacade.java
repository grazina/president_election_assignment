package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.dto.CandidateResultDto;
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

    public List<CandidateResultDto> getElectionResults() {
        final List<Voter> allVoters = voterService.getAllVoters();
        final List<Voter> activeVoters = allVoters.stream().filter(voter -> voter.getSelectedCandidate() != null).collect(Collectors.toList());
        final Map<Candidate, Long> candidateVotesMap = activeVoters.stream().collect(Collectors.groupingBy(Voter::getSelectedCandidate, Collectors.counting()));

        return candidateVotesMap.entrySet().stream()
                .map(candidateEntry -> makeCandidateResultDto(candidateEntry, allVoters.size(), activeVoters.size())).collect(Collectors.toList());
    }

    private CandidateResultDto makeCandidateResultDto(final Map.Entry<Candidate, Long> candidateEntry, final long totalVotersCount, final long activeVotersCount) {
        final CandidateResultDto candidateResultDto = new CandidateResultDto();
        candidateResultDto.setCandidate(candidateConverter.convert(candidateEntry.getKey()));
        candidateResultDto.setCandidateVotesCount(candidateEntry.getValue());
        candidateResultDto.setActiveVotersCount(activeVotersCount);
        candidateResultDto.setTotalVotersCount(totalVotersCount);
        return candidateResultDto;
    }

    public void setVoterService(final VoterService voterService) {
        this.voterService = voterService;
    }

    public void setCandidateConverter(final CandidateConverter candidateConverter) {
        this.candidateConverter = candidateConverter;
    }
}
