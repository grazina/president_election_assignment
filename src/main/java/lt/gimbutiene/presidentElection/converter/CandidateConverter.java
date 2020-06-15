package lt.gimbutiene.presidentElection.converter;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.dto.CandidateDto;
import org.springframework.stereotype.Component;

@Component
public class CandidateConverter {
    public CandidateDto convert(final Candidate candidate) {
        final CandidateDto candidateDto = new CandidateDto();
        candidateDto.setId(candidate.getId());
        candidateDto.setName(candidate.getName());
        candidateDto.setNumber(candidate.getNumber());
        candidateDto.setAgenda(candidate.getAgenda());
        return candidateDto;
    }
}
