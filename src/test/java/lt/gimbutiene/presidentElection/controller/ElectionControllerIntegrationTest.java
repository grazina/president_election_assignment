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
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ElectionControllerIntegrationTest {

    private static final Long VOTER_ID = 3L;
    private static final Long CANDIDATE_ID = 1L;
    private static final Long INVALID_ID = 0L;

    private static final String VOTING_URL = "/api/election/vote";
    private static final String GET_ELECTION_WINNER_URL = "/api/election/getElectionWinner";
    private static final String GET_CANDIDATES_URL = "/api/election/getCandidates";
    private static final String GET_ELECTION_RESULTS_URL = "/api/election/getElectionResults";

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
        return post(VOTING_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(voteDto));
    }


    private MockHttpServletRequestBuilder buildGetRequest(final String url) {
        return get(url).contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @Sql("election-controller-getWinner-single-winner.sql")
    public void shouldReturnSingleWinner() throws Exception {
        mockMvc.perform(buildGetRequest(GET_ELECTION_WINNER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.singleWinner", is(true)))
                .andExpect(jsonPath("$.firstCandidate.candidate.id", is(1)));
    }

    @Test
    @Sql("election-controller-getWinner-two-top-candidates.sql")
    public void shouldReturnTopTwoCandidates() throws Exception {
        mockMvc.perform(buildGetRequest(GET_ELECTION_WINNER_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.singleWinner", is(false)))
                .andExpect(jsonPath("$.firstCandidate.candidate.id", is(2)))
                .andExpect(jsonPath("$.secondCandidate.candidate.id", is(1)));
    }

    @Test
    @Sql("election-controller-getCandidates.sql")
    public void shouldReturnOrderedCandidates() throws Exception {
        mockMvc.perform(buildGetRequest(GET_CANDIDATES_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)))
                .andExpect(jsonPath("$[0].number", is(1)))
                .andExpect(jsonPath("$[8].number", is(9)));
    }

    @Test
    @Sql("election-controller-getElectionResults.sql")
    public void shouldReturnElectionResults() throws Exception {
        mockMvc.perform(buildGetRequest(GET_ELECTION_RESULTS_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.activeVotersCount", is(6)))
                .andExpect(jsonPath("$.totalVotersCount", is(7)))
                .andExpect(jsonPath("$.candidateResults", hasSize(2)))
                .andExpect(jsonPath("$.candidateResults[0].candidate.id", is(1)))
                .andExpect(jsonPath("$.candidateResults[0].candidateVotesCount", is(4)));
    }
}