package lt.gimbutiene.presidentElection.controller;

import lt.gimbutiene.presidentElection.dto.*;
import lt.gimbutiene.presidentElection.exception.VoterServiceException;
import lt.gimbutiene.presidentElection.facade.CandidateFacade;
import lt.gimbutiene.presidentElection.facade.VoterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("election")
public class ElectionController {

    @Autowired
    private CandidateFacade candidateFacade;

    @Autowired
    private VoterFacade voterFacade;

    @GetMapping("/getCandidates")
    public List<CandidateDto> getCandidates() {
        return candidateFacade.getCandidates();
    }

    @PostMapping("/vote")
    public void vote(@RequestBody final VoteDto voteDto) throws VoterServiceException {
        voterFacade.addVote(voteDto);
    }

    @GetMapping("/getElectionResults")
    public ElectionResultsByCandidateDto getElectionResults() {
        return voterFacade.getElectionResultsByCandidate();
    }

    @GetMapping("/getElectionResultsByRegion")
    public List<RegionResultsDto> getElectionResultsByRegion() {
        return voterFacade.getElectionResultsByRegion();
    }

    @GetMapping("/getElectionWinner")
    public WinnerDto getElectionWinner() {
        return voterFacade.getElectionWinner();
    }

}
