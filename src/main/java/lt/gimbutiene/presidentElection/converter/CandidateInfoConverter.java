package lt.gimbutiene.presidentElection.converter;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.dto.CandidateInfoDto;
import org.springframework.stereotype.Component;

@Component
public class CandidateInfoConverter {
    public CandidateInfoDto convert(final Candidate candidate) {
        final CandidateInfoDto candidateInfoDto = new CandidateInfoDto();
        candidateInfoDto.setId(candidate.getId());
        candidateInfoDto.setName(candidate.getName());
        candidateInfoDto.setNumber(candidate.getNumber());
        candidateInfoDto.setAgenda(candidate.getAgenda());
        return candidateInfoDto;
    }
}
