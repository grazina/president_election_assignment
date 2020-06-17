package lt.gimbutiene.presidentElection.service;

import lt.gimbutiene.presidentElection.domain.Region;
import lt.gimbutiene.presidentElection.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public void setRegionRepository(final RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
}
