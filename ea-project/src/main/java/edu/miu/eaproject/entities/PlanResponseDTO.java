package edu.miu.eaproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponseDTO {
    private Long id;
    private String name;
    private String description;
    private List<Location> locations =new ArrayList<>();
    private List<Role> roles;
}
