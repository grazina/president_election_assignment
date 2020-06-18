package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.dto.CandidateDto;

import java.util.List;

public interface CandidateFacade {
    List<CandidateDto> getCandidates();
}
