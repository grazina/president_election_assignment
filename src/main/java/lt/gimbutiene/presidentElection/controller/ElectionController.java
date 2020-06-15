package lt.gimbutiene.presidentElection.controller;

import lt.gimbutiene.presidentElection.dto.CandidateDto;
import lt.gimbutiene.presidentElection.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("election")
public class ElectionController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping
    public List<CandidateDto> getCandidates() {
        return candidateService.getCandidates();
    }

}
