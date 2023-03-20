package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.LocationType;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LocationDTO {

	private long id;
	private String name;
	private String description;
	private int capacity;
	private LocationType locationType;
	private List<TimeSlot> timeSlots;

	public LocationDTO(String name, String description, int capacity, LocationType locationType) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.locationType = locationType;
	}
}
