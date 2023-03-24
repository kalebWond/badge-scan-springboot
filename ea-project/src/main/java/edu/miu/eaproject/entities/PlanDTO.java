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
    private Long locationId;
    private Long roleId;




}
