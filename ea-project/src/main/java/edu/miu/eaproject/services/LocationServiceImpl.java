package edu.miu.eaproject.services;

import edu.miu.eaproject.entities.Location;
import edu.miu.eaproject.entities.LocationDTO;
import edu.miu.eaproject.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public LocationDTO save(LocationDTO location) {
        locationRepository.save(mapper.map(location, Location.class));
        return location;
    }
    @Override
    public List<LocationDTO> getAllLocations() {
        return getDtoList(locationRepository.findAll());
    }
    @Override
    public LocationDTO getLocationById(long id) {
        return getDto(locationRepository.findById(id).get());
    }

    @Override
    public LocationDTO updateLocationById(long id, LocationDTO locationDTO) {
        locationRepository.findById(id).orElseThrow(()->new RuntimeException("Location not found"));
        Location location=mapper.map(locationDTO,Location.class);
        locationRepository.save(location);
        return locationDTO;
    }
    @Override
    public void deleteLocationById(long id) {
        locationRepository.findById(id).orElseThrow(()->new RuntimeException("Location not found"));
        locationRepository.deleteById(id);
    }
    private List<LocationDTO> getDtoList(List<Location> locations) {
        return locations.stream().map(this::getDto).toList();
    }
    private LocationDTO getDto(Location location) {
        return mapper.map(location, LocationDTO.class);
    }
}
