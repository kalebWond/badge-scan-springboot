package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.LocationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_table")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    private LocationType locationType;
    @OneToMany
    @JoinColumn(name = "locationId")
    private List<Transaction> transactionList;
    @OneToMany
    @JoinTable(name = "location_timeslot_table")
    private List<TimeSlot> timeSlots;


}
