package lt.gimbutiene.presidentElection.service.impl;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.repository.CandidateRepository;
import lt.gimbutiene.presidentElection.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll(Sort.by("number"));
    }

    public void setCandidateRepository(final CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
}
