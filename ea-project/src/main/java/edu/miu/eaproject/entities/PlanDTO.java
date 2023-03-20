package edu.miu.eaproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {
    private Long id;
    private String name;
    private String description;
    private List<Location> locations;
    private List<Role> roles;
    private List<Long> locationIds;

    public PlanDTO(String name, String description, List<Long> locationIds) {
        this.name = name;
        this.description = description;
        this.locationIds = locationIds;
    }
    public PlanDTO(String name, String description) {
        this.name = name;
        this.description = description;

    }

}
