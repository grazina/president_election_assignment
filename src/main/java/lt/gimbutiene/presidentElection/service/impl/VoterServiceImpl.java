package lt.gimbutiene.presidentElection.service.impl;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.exception.VoterServiceException;
import lt.gimbutiene.presidentElection.repository.CandidateRepository;
import lt.gimbutiene.presidentElection.repository.VoterRepository;
import lt.gimbutiene.presidentElection.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterServiceImpl implements VoterService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Override
    public void addVote(final Long voterId, final Long selectedCandidateId) {
        final Voter voter = findVoter(voterId);
        if (voter.getSelectedCandidate() != null) {
            throw new VoterServiceException(String.format("Voter with ID=%d has already voted before.", voterId));
        }
        final Candidate candidate = findCandidate(selectedCandidateId);
        voter.setSelectedCandidate(candidate);
        voterRepository.save(voter);
    }

    private Voter findVoter(final Long voterId) {
        if (voterId == null) {
            throw new VoterServiceException("Voter Id must not be empty.");
        }
        final Optional<Voter> optionalVoter = voterRepository.findById(voterId);
        if (!optionalVoter.isPresent()) {
            throw new VoterServiceException(String.format("Could not find a voter with Id=%d.", voterId));
        }

        return optionalVoter.get();
    }

    private Candidate findCandidate(final Long candidateId) {
        if (candidateId == null) {
            throw new VoterServiceException("Selected candidate Id must not be empty.");
        }

        final Optional<Candidate> optionalCandidate = candidateRepository.findById(candidateId);
        if (!optionalCandidate.isPresent()) {
            throw new VoterServiceException(String.format("Could not find a candidate with Id=%d.", candidateId));
        }

        return optionalCandidate.get();
    }

    @Override
    public List<Voter> getAllVoters() {
        return voterRepository.findAll();
    }

    public void setCandidateRepository(final CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public void setVoterRepository(final VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }
}
