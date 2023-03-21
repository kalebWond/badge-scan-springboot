package edu.miu.eaproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plan_table")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany
    @JoinTable(name="plan_location_table")
    private List<Location> locations =new ArrayList<>();

    @OneToMany
    @JoinTable(name="plan_role_table")
    private List<Role> roles;
    public Plan(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void addLocation(Location location){
        locations.add(location);
    }
}
