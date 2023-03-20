package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Location;
import edu.miu.eaproject.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location getLocation(long id) {
        Location location = locationRepository.findById(id).get();
        return location;
    }


}
