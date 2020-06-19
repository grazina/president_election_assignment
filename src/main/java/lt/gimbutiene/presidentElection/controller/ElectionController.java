package lt.gimbutiene.presidentElection.controller;

import io.swagger.annotations.ApiOperation;
import lt.gimbutiene.presidentElection.dto.*;
import lt.gimbutiene.presidentElection.exception.VoterServiceException;
import lt.gimbutiene.presidentElection.facade.CandidateFacade;
import lt.gimbutiene.presidentElection.facade.VoterFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election")
public class ElectionController {

    @Autowired
    private CandidateFacade candidateFacade;

    @Autowired
    private VoterFacade voterFacade;

    @GetMapping("/getCandidates")
    @ApiOperation(value = "Get the list of candidates")
    public List<CandidateDto> getCandidates() {
        return candidateFacade.getCandidates();
    }


    @PostMapping("/vote")
    @ApiOperation(value = "Vote for a candidate")
    public void vote(@RequestBody final VoteDto voteDto) throws VoterServiceException {
        voterFacade.addVote(voteDto);
    }

    @GetMapping("/getElectionResults")
    @ApiOperation(value = "Get election result for each candidate")
    public ElectionResultsByCandidateDto getElectionResults() {
        return voterFacade.getElectionResultsByCandidate();
    }


    @GetMapping("/getElectionResultsByRegion")
    @ApiOperation(value = "Get election result for each candidate by region")
    public List<RegionResultsDto> getElectionResultsByRegion() {
        return voterFacade.getElectionResultsByRegion();
    }

    @GetMapping("/getElectionWinner")
    @ApiOperation(value = "Get the winner of the election")
    public WinnerDto getElectionWinner() {
        return voterFacade.getElectionWinner();
    }

}
