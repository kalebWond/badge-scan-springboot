package edu.miu.eaproject.services;


import edu.miu.eaproject.entities.LocationDTO;

import java.util.List;

public interface LocationService {
    public LocationDTO save(LocationDTO location);
    public LocationDTO getLocationById(long id);
    public void deleteLocationById(long id);
    public List<LocationDTO> getAllLocations();
    public LocationDTO updateLocationById(long id, LocationDTO locationDTO);
}
