package edu.miu.eaproject.controllers;

import edu.miu.eaproject.entities.LocationDTO;
import edu.miu.eaproject.entities.PlanDTO;
import edu.miu.eaproject.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> save(@RequestBody LocationDTO location){
        return new ResponseEntity<>(locationService.save(location), HttpStatus.OK);
    }
//    public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanDTO planDTO){
//        return new ResponseEntity<>(planService.createPlan(planDTO), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> getAllMembers(){
        List<LocationDTO> locations = locationService.getAllLocations();
        return new ResponseEntity<List<LocationDTO>>(locations, HttpStatus.OK);
    }

    @GetMapping ("/{locationId}")
    public ResponseEntity<?> getLocation(@PathVariable("locationId") long locationId){
        LocationDTO location = locationService.getLocationById(locationId);
        return new ResponseEntity<LocationDTO>(location, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        locationService.deleteLocationById(id);
        return new ResponseEntity<String>("Location successfully deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody LocationDTO location) {
        LocationDTO updated = locationService.updateLocationById(id, location);
        return new ResponseEntity<LocationDTO>(updated, HttpStatus.OK);
    }
}
