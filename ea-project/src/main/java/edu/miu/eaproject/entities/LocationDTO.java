package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.LocationType;

import lombok.Data;

import java.util.List;

@Data
public class LocationDTO {
	private long id;
	private String name;
	private String description;
	private int capacity;
	private LocationType locationType;
	private List<TimeSlot> timeSlots;
}
