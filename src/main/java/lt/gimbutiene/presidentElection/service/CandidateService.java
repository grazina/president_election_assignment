package lt.gimbutiene.presidentElection.service;

import lt.gimbutiene.presidentElection.converter.CandidateConverter;
import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.dto.CandidateDto;
import lt.gimbutiene.presidentElection.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateConverter candidateConverter;

    public List<CandidateDto> getCandidates() {
        final List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().map(c -> candidateConverter.convert(c)).collect(Collectors.toList());
    }
}
