package edu.miu.eaproject.entities;

import edu.miu.eaproject.entities.enums.MembershipType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "membership_table")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private MembershipType membershipType;

    private int numberOfAllowances;

    @OneToMany
    @JoinColumn(name = "membershipId")
    private List<Transaction> transactionList;


    @OneToOne
    private Plan plan;
// add current allowance count
}
