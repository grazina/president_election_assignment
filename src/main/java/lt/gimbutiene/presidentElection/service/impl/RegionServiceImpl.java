package lt.gimbutiene.presidentElection.service.impl;

import lt.gimbutiene.presidentElection.domain.Region;
import lt.gimbutiene.presidentElection.repository.RegionRepository;
import lt.gimbutiene.presidentElection.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public void setRegionRepository(final RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
}
