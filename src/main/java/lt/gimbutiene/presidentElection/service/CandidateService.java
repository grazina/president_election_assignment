package lt.gimbutiene.presidentElection.service;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public void setCandidateRepository(final CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }
}
