package lt.gimbutiene.presidentElection.controller;

import lt.gimbutiene.presidentElection.dto.CandidateDto;
import lt.gimbutiene.presidentElection.dto.VoteDto;
import lt.gimbutiene.presidentElection.exception.VoterServiceException;
import lt.gimbutiene.presidentElection.service.CandidateService;
import lt.gimbutiene.presidentElection.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("election")
public class ElectionController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoterService voterService;

    @GetMapping
    public List<CandidateDto> getCandidates() {
        return candidateService.getCandidates();
    }

    @PostMapping("/vote")
    public void vote(@RequestBody final VoteDto voteDto) throws VoterServiceException {
        voterService.addVote(voteDto);
    }
}
