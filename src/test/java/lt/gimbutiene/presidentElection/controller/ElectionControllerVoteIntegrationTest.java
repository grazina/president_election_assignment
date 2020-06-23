package lt.gimbutiene.presidentElection.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lt.gimbutiene.presidentElection.domain.Voter;
import lt.gimbutiene.presidentElection.dto.VoteDto;
import lt.gimbutiene.presidentElection.repository.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ElectionControllerVoteIntegrationTest {

    private static final Long VOTER_ID = 3L;
    private static final Long CANDIDATE_ID = 1L;
    private static final Long INVALID_ID = 0L;
    private static final String VOTING_URL = "/api/election/vote";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VoterRepository voterRepository;

    final VoteDto voteDto = new VoteDto();

    @BeforeEach
    public void setUp() {
        voteDto.setVoterId(VOTER_ID);
        voteDto.setSelectedCandidateId(CANDIDATE_ID);
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldSaveTheVote() throws Exception {
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isOk());

        final Optional<Voter> optionalVoter = voterRepository.findById(VOTER_ID);
        assertTrue(optionalVoter.isPresent());
        final Voter voter = optionalVoter.get();
        assertNotNull(voter.getSelectedCandidate());
        assertEquals(voter.getSelectedCandidate().getId(), CANDIDATE_ID);
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldRejectRepeatedVote() throws Exception {
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isOk());
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isBadRequest());
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldRejectEmptyVoterId() throws Exception {
        voteDto.setVoterId(null);
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isBadRequest());
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldRejectInvalidVoterId() throws Exception {
        voteDto.setVoterId(INVALID_ID);
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isBadRequest());
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldRejectEmptySelectedCandidateId() throws Exception {
        voteDto.setSelectedCandidateId(null);
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isBadRequest());
    }

    @Test
    @Sql("election-controller-vote.sql")
    void shouldRejectInvalidSelectedCandidateId() throws Exception {
        voteDto.setSelectedCandidateId(INVALID_ID);
        mockMvc.perform(buildVotingRequest(voteDto)).andExpect(status().isBadRequest());
    }

    private MockHttpServletRequestBuilder buildVotingRequest(final VoteDto voteDto) throws JsonProcessingException {
        return post(VOTING_URL).contentType("application/json").content(objectMapper.writeValueAsString(voteDto));
    }
}