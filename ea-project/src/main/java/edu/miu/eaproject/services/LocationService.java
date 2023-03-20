package edu.miu.eaproject.services;


import edu.miu.eaproject.entities.Location;

public interface LocationService {
    public void save(Location location);
    public Location getLocation(long id);
}
