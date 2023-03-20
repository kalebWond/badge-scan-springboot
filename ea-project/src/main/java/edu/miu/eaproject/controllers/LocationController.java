package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.Location;
import edu.miu.eaproject.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public void save(@RequestBody Location location){
        locationService.save(location);
    }

    @GetMapping ("/{locationId}")
    public ResponseEntity<?> getLocation(@PathVariable("locationId") long locationId){
        Location location = locationService.getLocation(locationId);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }
}
