package lt.gimbutiene.presidentElection.repository;

import lt.gimbutiene.presidentElection.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
