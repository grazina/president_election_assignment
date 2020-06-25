package lt.gimbutiene.presidentElection.converter;

import lt.gimbutiene.presidentElection.domain.Candidate;
import lt.gimbutiene.presidentElection.dto.CandidateBaseDto;
import org.springframework.stereotype.Component;

@Component
public class CandidateBaseConverter {
    public CandidateBaseDto convert(final Candidate candidate) {
        final CandidateBaseDto candidateBaseDto = new CandidateBaseDto();
        candidateBaseDto.setId(candidate.getId());
        candidateBaseDto.setName(candidate.getName());
        return candidateBaseDto;
    }
}
