package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.dto.CandidateDto;
import lt.gimbutiene.presidentElection.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidatesFacade {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateConverter candidateConverter;

    public List<CandidateDto> getCandidates() {
        return candidateService.getAllCandidates().stream().map(c -> candidateConverter.convert(c)).collect(Collectors.toList());
    }
}
