package lt.gimbutiene.presidentElection.service;

import lt.gimbutiene.presidentElection.domain.Voter;

import java.util.List;

public interface VoterService {
    void addVote(final Long voterId, final Long selectedCandidateId);

    List<Voter> getAllVoters();
}