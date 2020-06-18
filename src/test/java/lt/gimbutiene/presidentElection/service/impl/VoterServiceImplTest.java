package lt.gimbutiene.presidentElection.service.impl;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.exception.VoterServiceException;
import lt.gimbutiene.presidentElection.repository.CandidateRepository;
import lt.gimbutiene.presidentElection.repository.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class VoterServiceImplTest {

    private final static Long VOTER_ID = 1L;
    private final static Long CANDIDATE_ID = 1L;

    private final VoterServiceImpl voterService = new VoterServiceImpl();
    private final CandidateRepository candidateRepositoryMock = mock(CandidateRepository.class);
    private final VoterRepository voterRepositoryMock = mock(VoterRepository.class);

    private final Voter voterMock = mock(Voter.class);
    private final Candidate candidateMock = mock(Candidate.class);

    @BeforeEach
    public void setUp() {
        when(candidateRepositoryMock.findById(anyLong())).thenReturn(Optional.of(candidateMock));
        when(voterRepositoryMock.findById(anyLong())).thenReturn(Optional.of(voterMock));

        voterService.setCandidateRepository(candidateRepositoryMock);
        voterService.setVoterRepository(voterRepositoryMock);
    }

    @Test
    public void shouldSaveValidVote() {
        voterService.addVote(VOTER_ID, CANDIDATE_ID);
        verify(voterMock).setSelectedCandidate(candidateMock);
        verify(voterRepositoryMock).save(voterMock);
    }

    @Test
    public void shouldThrowExceptionIfVoterIdIsEmpty() {
        assertThrows(VoterServiceException.class, () ->
                voterService.addVote(null, CANDIDATE_ID));
    }

    @Test
    public void shouldThrowExceptionIfVoterIdIsUnknown() {
        when(voterRepositoryMock.findById(VOTER_ID)).thenReturn(Optional.empty());
        assertThrows(VoterServiceException.class, () ->
                voterService.addVote(VOTER_ID, CANDIDATE_ID));
    }

    @Test
    public void shouldThrowExceptionIfSelectedCandidateIdIsEmpty() {
        assertThrows(VoterServiceException.class, () ->
                voterService.addVote(VOTER_ID, null));
    }

    @Test
    public void shouldThrowExceptionIfCandidateIdIsUnknown() {
        when(candidateRepositoryMock.findById(CANDIDATE_ID)).thenReturn(Optional.empty());
        assertThrows(VoterServiceException.class, () ->
                voterService.addVote(VOTER_ID, CANDIDATE_ID));
    }

    @Test
    public void shouldThrowExceptionWhenVotingAgain() {
        when(voterMock.getSelectedCandidate()).thenReturn(candidateMock);
        assertThrows(VoterServiceException.class, () ->
                voterService.addVote(VOTER_ID, CANDIDATE_ID));
    }
}