package lt.gimbutiene.presidentElection.repository;

import lt.gimbutiene.presidentElection.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
}
