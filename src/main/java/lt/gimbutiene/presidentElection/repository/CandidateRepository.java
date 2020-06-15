package lt.gimbutiene.presidentElection.repository;

import lt.gimbutiene.presidentElection.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
