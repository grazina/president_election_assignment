package lt.gimbutiene.presidentElection.facade;

import lt.gimbutiene.presidentElection.dto.CandidateInfoDto;

import java.util.List;

public interface CandidateFacade {
    List<CandidateInfoDto> getCandidates();
}
