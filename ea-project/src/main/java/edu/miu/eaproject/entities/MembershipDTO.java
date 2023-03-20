package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.MembershipType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MembershipDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipType membershipType;
    private int numberOfAllowances;

}
