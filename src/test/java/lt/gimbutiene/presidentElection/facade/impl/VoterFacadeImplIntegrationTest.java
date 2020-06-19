package lt.gimbutiene.presidentElection.facade.impl;

import lt.gimbutiene.presidentElection.dto.WinnerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VoterFacadeImplIntegrationTest {

    @Autowired
    private VoterFacadeImpl voterFacade;

    @Test
    @Sql("single-winner.sql")
    public void shouldReturnSingleWinner() {
        final WinnerDto winnerDto = voterFacade.getElectionWinner();
        assertTrue(winnerDto.isSingleWinner());
        assertEquals(winnerDto.getFirstCandidate().getCandidate().getId(), 1L);
    }

    @Test
    @Sql("two-top-candidates.sql")
    public void shouldReturnTopTwoCandidates() {
        final WinnerDto winnerDto = voterFacade.getElectionWinner();
        assertFalse(winnerDto.isSingleWinner());
        assertEquals(winnerDto.getFirstCandidate().getCandidate().getId(), 2L);
        assertEquals(winnerDto.getSecondCandidate().getCandidate().getId(), 1L);
    }
}