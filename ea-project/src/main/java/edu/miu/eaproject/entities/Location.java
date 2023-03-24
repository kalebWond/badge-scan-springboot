package edu.miu.eaproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.eaproject.entities.enums.LocationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_table")
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private int capacity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LocationType locationType;

//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "location")
//    //@JoinColumn(name = "locationId")
//    @JsonIgnore
//    private List<Transaction> transactionList;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "location_timeslot_table")
    private List<TimeSlot> timeSlots;


}
