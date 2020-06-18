package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.dto.ElectionResultsByCandidateDto;
import lt.gimbutiene.presidentElection.dto.RegionResultsDto;
import lt.gimbutiene.presidentElection.dto.VoteDto;
import lt.gimbutiene.presidentElection.dto.WinnerDto;

import java.util.List;

public interface VoterFacade {
    void addVote(final VoteDto voteDto);

    ElectionResultsByCandidateDto getElectionResultsByCandidate();

    List<RegionResultsDto> getElectionResultsByRegion();

    WinnerDto getElectionWinner();
}
